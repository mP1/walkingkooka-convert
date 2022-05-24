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
 * A Converter that converts a String with one character into a {@link Character}. Strings of other length will fail.
 */
final class ConverterStringCharacter<C extends ConverterContext> extends Converter2<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterStringCharacter<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterStringCharacter<?> INSTANCE = new ConverterStringCharacter<>();


    /**
     * Private to stop sub classing.
     */
    private ConverterStringCharacter() {
        super();
    }

    @Override
    boolean canConvertNonNull(final Object value,
                              final Class<?> type,
                              final C context) {
        return value instanceof String && ((String) value).length() == 1;
    }

    @Override
    boolean canConvertType(final Class<?> type) {
        return type == Character.class;
    }

    @Override <T> Either<T, String> convertNonNull(final Object value,
                                                   final Class<T> type,
                                                   final ConverterContext context) {
        final String string = (String) value;
        final int length = string.length();

        return length == 1 ?
                this.successfulConversion(
                        string.charAt(0),
                        type
                ) :
                Either.right("String length must be one but was " + length);
    }

    @Override
    public String toString() {
        return "String->Character";
    }
}
