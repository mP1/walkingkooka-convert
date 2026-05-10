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
import walkingkooka.currency.CurrencyExchange;
import walkingkooka.currency.CurrencyValue;

import java.util.Optional;

/**
 * A {@link Converter} that handles converting {@link CurrencyValue} number component into {@link Number}, converting
 * the source value into the current {@link walkingkooka.currency.CurrencyCode}.
 */
final class ConverterCurrencyValueToNumber<C extends ConverterContext> implements ShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterCurrencyValueToNumber<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterCurrencyValueToNumber<?> INSTANCE = new ConverterCurrencyValueToNumber<>();

    private ConverterCurrencyValueToNumber() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return value instanceof CurrencyValue && context.canConvert(
            ((CurrencyValue) value).value(),
            type
        );
    }

    @Override
    public <T> Either<T, String> doConvert(final Object value,
                                           final Class<T> type,
                                           final C context) {
        Either<T, String> result = null;

        final CurrencyValue currencyValue = (CurrencyValue) value;

        // 1. convert CurrencyValue.value to $type
        result = context.convert(
            currencyValue.value(),
            type
        );

        if (result.isLeft()) {
            final T currencyValueNumber = result.leftValue();

            // 2. get exchange rate
            final CurrencyCode fromCurrencyCode = currencyValue.currencyCode();
            final CurrencyCode toCurrencyCode = context.currencyCode();

            final CurrencyExchange currencyExchange = CurrencyExchange.with(
                fromCurrencyCode,
                toCurrencyCode
            );

            // get exchangeRate might not be available.
            final Number exchangeRateOrNull = context.currencyExchangeRate(
                currencyExchange,
                Optional.empty() // dateTime
            ).orElse(null);
            if (null == exchangeRateOrNull) {
                result = Either.right("Unable to convert " + value + " from " + fromCurrencyCode + " to " + toCurrencyCode);
            } else {
                // 3. convert exchangeRate to $type
                result = context.convert(
                    exchangeRateOrNull,
                    type
                );
                if (result.isLeft()) {
                    // 4. multiply currencyValueNumber and exchangeRateAsType
                    result = this.successfulConversion(
                        context.multiply(
                            (Number) currencyValueNumber,
                            exchangeRateOrNull,
                            Cast.to(type)
                        ),
                        type
                    );
                }
            }
        }

        return result;
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return CurrencyValue.class.getSimpleName() + " to " + Number.class.getSimpleName();
    }
}
