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
import walkingkooka.currency.CurrencyCode;
import walkingkooka.currency.CurrencyValue;

/**
 * Converter that accepts a {@link Number} returning a {@link walkingkooka.currency.CurrencyValue} using the currenty {@link CurrencyCode}.
 * The actual Number value is not modified or validated in anyway.
 */
final class ConverterNumberToCurrencyValue<C extends ConverterContext> implements ShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterNumberToCurrencyValue<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterNumberToCurrencyValue<?> INSTANCE = new ConverterNumberToCurrencyValue<>();

    private ConverterNumberToCurrencyValue() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return value instanceof Number && CurrencyValue.class == type;
    }

    @Override
    public <T> Either<T, String> doConvert(final Object value,
                                           final Class<T> type,
                                           final C context) {
        return this.successfulConversion(
            CurrencyValue.with(
                (Number)value,
                context.currencyCode()
            ),
            type
        );
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return Number.class.getSimpleName() + " to " + CurrencyValue.class.getSimpleName();
    }
}
