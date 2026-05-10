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

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;
import walkingkooka.Either;
import walkingkooka.currency.CurrencyCode;
import walkingkooka.currency.CurrencyExchange;
import walkingkooka.currency.CurrencyValue;

import java.time.LocalDateTime;
import java.util.Optional;

public final class ConverterCurrencyValueToNumberTest extends ConverterTestCase2<ConverterCurrencyValueToNumber<ConverterContext>> {

    private final static CurrencyCode FROM_CURRENCY_CODE = CurrencyCode.parse("AUD");
    private final static CurrencyCode TO_CURRENCY_CODE = CurrencyCode.parse("NZD");

    @Test
    public void testConvertCurrencyCodeWithUnsupportedCurrencyExchange() {
        this.convertFails(
            CurrencyValue.with(
                12.0,
                CurrencyCode.parse("CAD")
            ),
            Integer.class
        );
    }

    @Test
    public void testConvertCurrencyCodeDoubleToDouble() {
        this.convertAndCheck(
            CurrencyValue.with(
                12.0,
                FROM_CURRENCY_CODE
            ),
            Integer.class,
            12 * 2
        );
    }

    @Test
    public void testConvertCurrencyCodeIntegerToInteger() {
        this.convertAndCheck(
            CurrencyValue.with(
                12,
                FROM_CURRENCY_CODE
            ),
            Integer.class,
            12 * 2
        );
    }

    @Override
    public ConverterCurrencyValueToNumber<ConverterContext> createConverter() {
        return ConverterCurrencyValueToNumber.instance();
    }

    @Override
    public ConverterContext createContext() {
        return new FakeConverterContext() {

            @Override
            public boolean canConvert(final Object value,
                                      final Class<?> type) {
                return this.converter.canConvert(
                    value,
                    type,
                    this
                );
            }

            @Override
            public <T> Either<T, String> convert(final Object value,
                                                 final Class<T> type) {
                return this.converter.convert(
                    value,
                    type,
                    this
                );
            }

            private final Converter<ConverterContext> converter = Converters.numberToNumber();

            @Override
            public CurrencyCode currencyCode() {
                return TO_CURRENCY_CODE;
            }

            @Override
            public Optional<Number> currencyExchangeRate(final CurrencyExchange currencyExchange,
                                                         final Optional<LocalDateTime> dateTime) {
                return Optional.ofNullable(
                    CurrencyExchange.with(
                        FROM_CURRENCY_CODE,
                        TO_CURRENCY_CODE
                    ).equals(currencyExchange) ?
                        2.0 :
                        null
                );
            }

            @Override
            public <N extends Number> N multiply(final Number left,
                                                 final Number right,
                                                 final Class<N> type) {
                return BinaryNumberConverterFunctions.multiply()
                    .apply(
                        left,
                        right,
                        type,
                        this
                    );
            }
        };
    }

    @Override
    public Class<ConverterCurrencyValueToNumber<ConverterContext>> type() {
        return Cast.to(ConverterCurrencyValueToNumber.class);
    }
}
