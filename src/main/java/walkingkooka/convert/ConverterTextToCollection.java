/*
 * Copyright 2019 Miroslav Pokorny (github.com/mP1)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package walkingkooka.convert;

import walkingkooka.InvalidCharacterException;
import walkingkooka.NeverError;

import java.util.Collection;

/**
 * Base class that includes the logic for parsing a string with a separator and converting each element to the element type.
 */
abstract class ConverterTextToCollection<IMMUTABLE_COLLECTION extends Collection<E>, MUTABLE_COLLECTION extends Collection<E>, E, CONTEXT extends ConverterContext> extends ConverterTextTo<IMMUTABLE_COLLECTION, CONTEXT> {

    ConverterTextToCollection() {
        super();
    }

    @Override
    public final Object parseText(final String text,
                                  final Class<?> type,
                                  final CONTEXT context) {
        final int MODE_OUTSIDE_QUOTES = 1;
        final int MODE_INSIDE_QUOTES = 2;
        final int MODE_INSIDE_QUOTES_ESCAPE_NEXT = 3;
        final int MODE_AFTER_QUOTES = 4;

        final char DOUBLE_QUOTES = '"';

        int mode = MODE_OUTSIDE_QUOTES;

        final char separator = context.valueSeparator();

        final MUTABLE_COLLECTION mutableCollection = this.mutableCollection();

        final StringBuilder element = new StringBuilder();
        int position = 0;

        for (final char c : text.toCharArray()) {
            switch (mode) {
                case MODE_OUTSIDE_QUOTES:
                    switch (c) {
                        case DOUBLE_QUOTES:
                            mode = MODE_INSIDE_QUOTES;
                            break;
                        default:
                            if (separator == c) {
                                this.addElement(
                                    element,
                                    mutableCollection,
                                    context
                                );
                                break;
                            } else {
                                element.append(c);
                            }
                    }
                    break;
                case MODE_INSIDE_QUOTES:
                    switch (c) {
                        case DOUBLE_QUOTES:
                            this.addElement(
                                element,
                                mutableCollection,
                                context
                            );
                            mode = MODE_AFTER_QUOTES;
                            break;
                        case '\\':
                            // only spaces before quotes are allowed, otherwise ICE
                            if (false == element.toString()
                                .trim()
                                .isEmpty()) {
                                throw new InvalidCharacterException(
                                    text,
                                    position
                                );
                            }

                            mode = MODE_INSIDE_QUOTES_ESCAPE_NEXT;
                            break;
                        default:
                            if (separator == c) {
                                this.addElement(
                                    element,
                                    mutableCollection,
                                    context
                                );
                            } else {
                                element.append(c);
                            }
                            break;
                    }
                    break;
                case MODE_INSIDE_QUOTES_ESCAPE_NEXT:
                    element.append(c);
                    mode = MODE_INSIDE_QUOTES;
                    break;
                case MODE_AFTER_QUOTES:
                    if (separator == c) {
                        mode = MODE_OUTSIDE_QUOTES;
                    } else {
                        if (false == Character.isWhitespace(c)) {
                            throw new InvalidCharacterException(
                                text,
                                position
                            );
                        }
                    }
                    break;
                default:
                    NeverError.unhandledCase(
                        mode,
                        MODE_OUTSIDE_QUOTES,
                        MODE_INSIDE_QUOTES,
                        MODE_INSIDE_QUOTES_ESCAPE_NEXT,
                        MODE_AFTER_QUOTES
                    );
                    break;
            }

            position++;
        }

        switch (mode) {
            case MODE_OUTSIDE_QUOTES:
                final String elementToString = element.toString()
                    .trim();
                if(elementToString.isEmpty()) {
                    // must be trailing comma and nothing.. throw ICE!
                    if(false == mutableCollection.isEmpty()) {
                        throw new InvalidCharacterException(
                            text,
                            position -1
                        );
                    }
                } else {
                    addElement(
                        element,
                        mutableCollection,
                        context
                    );
                }
                break;
            case MODE_INSIDE_QUOTES:
            case MODE_INSIDE_QUOTES_ESCAPE_NEXT:
                throw new IllegalArgumentException("Unclosed quotes");
            case MODE_AFTER_QUOTES:
                break;
            default:
                NeverError.unhandledCase(
                    mode,
                    MODE_OUTSIDE_QUOTES,
                    MODE_INSIDE_QUOTES,
                    MODE_INSIDE_QUOTES_ESCAPE_NEXT,
                    MODE_AFTER_QUOTES
                );
                break;
        }

        return this.toImmutableCollection(mutableCollection);
    }

    abstract MUTABLE_COLLECTION mutableCollection();

    private void addElement(final StringBuilder element,
                            final MUTABLE_COLLECTION mutableCollection,
                            final CONTEXT context) {
        mutableCollection.add(
            context.convertOrFail(
                element.toString()
                    .trim(),
                this.elementType()
            )
        );

        element.setLength(0);

    }

    abstract Class<E> elementType();

    /**
     * The converted elements will be added to this empty {@link Collection} giving the target type.
     */
    abstract IMMUTABLE_COLLECTION toImmutableCollection(final MUTABLE_COLLECTION mutableCollection);
}
