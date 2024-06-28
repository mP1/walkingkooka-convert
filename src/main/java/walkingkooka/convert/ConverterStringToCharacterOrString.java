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

/**
 * A Converter that supports converting String to Character if string is 1 char or a String..
 */
final class ConverterStringToCharacterOrString<C extends ConverterContext> extends Converter2<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterStringToCharacterOrString<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterStringToCharacterOrString<?> INSTANCE = new ConverterStringToCharacterOrString<>();


    /**
     * Private to stop sub classing.
     */
    private ConverterStringToCharacterOrString() {
        super();
    }

    @Override
    boolean canConvertNonNull(final Object value,
                              final Class<?> type,
                              final C context) {
        return Character.class == type && value instanceof String && ((String) value).length() == 1 ||
                String.class == type;
    }

    @Override
    boolean canConvertType(final Class<?> type) {
        return type == Character.class ||
                type == String.class;
    }

    @Override //
    <T> Either<T, String> convertNonNull(final Object value,
                                         final Class<T> type,
                                         final ConverterContext context) {
        // String -> String always succeeds.
        return value instanceof String && String.class == type ?
                this.successfulConversion(
                        value,
                        type
                ) :
                // Character -> String always works
                value instanceof Character && String.class == type ?
                        this.successfulConversion(
                                value.toString(),
                                type
                        ) :
                        // String -> Character will fail if String length is not 1.
                        value instanceof String && Character.class == type ?
                                this.convertStringToCharacter(
                                        (String) value,
                                        type
                                ) :
                                this.failConversion(
                                        value,
                                        type
                                );
    }

    private <T> Either<T, String> convertStringToCharacter(final String value,
                                                           final Class<T> type) {
        final int length = value.length();

        return 1 == length ?
                this.successfulConversion(
                        value.charAt(0),
                        type
                ) :
                Either.right("String length must be one but was " + length);
    }

    @Override
    public String toString() {
        return "String to Character or String";
    }
}
