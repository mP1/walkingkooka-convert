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
 * A {@link Converter} that supports considering {@link Character} as equivalent to {@link String}.
 * Note if the input is a {@link String} then that will be returned.
 */
final class ConverterCharacterOrStringToString<C extends ConverterContext> implements ShortCircuitingConverter<C> {

    static <C extends ConverterContext> ConverterCharacterOrStringToString<C> instance() {
        return Cast.to(INSTANCE);
    }

    private final static ConverterCharacterOrStringToString<ConverterContext> INSTANCE = new ConverterCharacterOrStringToString<>();

    private ConverterCharacterOrStringToString() {
        super();
    }

    // ShortCircuitingConverter.........................................................................................

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return String.class == type &&
            (
                null == value ||
                    value instanceof Character ||
                    value instanceof String
            );
    }

    @Override
    public <T> Either<T, String> doConvert(final Object value,
                                           final Class<T> type,
                                           final C context) {
        return this.successfulConversion(
            null != value ? value.toString() : null,
            type
        );
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return "Character or String to String";
    }
}
