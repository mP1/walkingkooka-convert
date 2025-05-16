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

import walkingkooka.Cast;
import walkingkooka.Either;
import walkingkooka.EmptyTextException;
import walkingkooka.InvalidTextLengthException;
import walkingkooka.text.HasText;

/**
 * A Converter that supports any text like source such as {@link Character} {@link CharSequence}, {@link walkingkooka.text.HasText} or {@link String} to either
 * {@link Character} or {@link CharSequence} or {@link String}.
 * If the target type is {@link CharSequence} then {@link HasText#text()} or the original value will be returned.
 */
final class ConverterCharacterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString<C extends ConverterContext> extends Converter2<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterCharacterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterCharacterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString<?> INSTANCE = new ConverterCharacterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString<>();

    /**
     * Private to stop sub classing.
     */
    private ConverterCharacterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString() {
        super();
    }

    @Override
    boolean canConvertNonNull(final Object value,
                              final Class<?> type,
                              final C context) {
        return value instanceof Character ||
                value instanceof CharSequence ||
                value instanceof HasText ||
                value instanceof String;
    }

    @Override
    boolean canConvertType(final Class<?> type) {
        return Character.class == type ||
                CharSequence.class == type ||
                String.class == type;
    }

    @Override //
    <T> Either<T, String> convertNonNull(final Object value,
                                         final Class<T> type,
                                         final ConverterContext context) {
        Either<T, String> either;

        try {
            Object result;

            switch (type.getSimpleName()) {
                case "CharSequence":
                    if (value instanceof Character) {
                        result = String.valueOf(value);
                    } else {
                        if (value instanceof HasText) {
                            result = ((HasText) value).text();
                        } else {
                            // CharSequence | String say unchanged/
                            result = value;
                        }
                    }
                    break;
                case "Character":
                    if (value instanceof Character) {
                        result = value; // Character remains Character
                    } else {
                        CharSequence charSequence;
                        if (value instanceof CharSequence) {
                            charSequence = (CharSequence) value;
                        } else {
                            if (value instanceof HasText) {
                                charSequence = ((HasText) value).text();
                            } else {
                                charSequence = toString().toString();
                            }
                        }

                        // extract the first character or complain
                        final int length = charSequence.length();
                        switch (length) {
                            case 0:
                                throw new EmptyTextException("Empty text");
                            case 1:
                                result = charSequence.charAt(0);
                                break;
                            default:
                                throw new InvalidTextLengthException(
                                        "value",
                                        charSequence.toString(),
                                        1,
                                        1
                                );
                        }
                    }
                    break;
                case "String":
                    if (value instanceof HasText) {
                        result = ((HasText) value).text(); // HasText#String
                    } else {
                        // CharSequence | String say unchanged/
                        result = value.toString();
                    }
                    break;
                default:
                    throw new UnsupportedOperationException(); // never happens.
            }

            either = this.successfulConversion(
                    result,
                    type
            );
        } catch (final RuntimeException cause) {
            either = this.failConversion(
                    value,
                    type,
                    cause
            );
        }

        return either;
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return "Character or CharSequence or HasText or String to Character or CharSequence or String";
    }
}
