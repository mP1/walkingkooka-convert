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
import walkingkooka.collect.list.ImmutableList;
import walkingkooka.collect.list.Lists;

import java.util.List;

/**
 * Base class that includes the logic for parsing a string with a separator and converting each element to the element type.
 */
abstract class ConverterTextToList<L extends ImmutableList<E>, E, C extends ConverterContext> implements TextToTryingShortCircuitingConverter<C> {

    ConverterTextToList() {
        super();
    }


    @Override
    public boolean isTargetType(final Object value,
                                final Class<?> type,
                                final C context) {
        return this.listType() == type;
    }

    abstract Class<L> listType();

    @Override
    public final Object parseText(final String text,
                                  final Class<?> type,
                                  final C context) {
        final int MODE_OUTSIDE_QUOTES = 1;
        final int MODE_INSIDE_QUOTES = 2;
        final int MODE_INSIDE_QUOTES_ESCAPE_NEXT = 3;
        final int MODE_AFTER_QUOTES = 4;

        final char DOUBLE_QUOTES = '"';

        int mode = MODE_OUTSIDE_QUOTES;

        final char separator = context.valueSeparator();

        final List<E> list = Lists.array();
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
                                    list,
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
                                list,
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
                                    list,
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
                    if(false == list.isEmpty()) {
                        throw new InvalidCharacterException(
                            text,
                            position -1
                        );
                    }
                } else {
                    addElement(
                        element,
                        list,
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

        return this.emptyList()
            .setElements(list);
    }

    private void addElement(final StringBuilder element,
                            final List<E> list,
                            final C context) {
        list.add(
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
     * The converted elements will be added to this empty list giving the target.
     */
    abstract L emptyList();

    // Object...........................................................................................................

    @Override
    public final String toString() {
        return "text to " + this.listType().getSimpleName();
    }
}
