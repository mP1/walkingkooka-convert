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

import java.util.Currency;

/**
 * Converter that accepts a {@link walkingkooka.currency.CurrencyCode} returning the matching {@link java.util.Currency}.
 */
final class ConverterCurrencyCodeToCurrency<C extends ConverterContext> implements ShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterCurrencyCodeToCurrency<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterCurrencyCodeToCurrency<?> INSTANCE = new ConverterCurrencyCodeToCurrency<>();

    private ConverterCurrencyCodeToCurrency() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return value instanceof CurrencyCode && Currency.class == type;
    }

    @Override
    public <T> Either<T, String> doConvert(final Object value,
                                           final Class<T> type,
                                           final C context) {
        final Currency currency = context.currencyForCurrencyCode(
            (CurrencyCode) value
        ).orElse(null);

        return null != currency ?
            this.successfulConversion(
                currency,
                type
            ) :
            this.failConversion(
                value,
                type
            );
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return CurrencyCode.class.getSimpleName() + " to " + Currency.class.getSimpleName();
    }
}
