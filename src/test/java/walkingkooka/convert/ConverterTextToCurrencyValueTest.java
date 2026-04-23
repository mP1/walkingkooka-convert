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
import walkingkooka.collect.list.Lists;
import walkingkooka.currency.CurrencyCode;
import walkingkooka.currency.CurrencyValue;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

public final class ConverterTextToCurrencyValueTest extends ConverterTextToTestCase<ConverterTextToCurrencyValue<ConverterContext>, CurrencyValue> {

    private final static CurrencyCode CURRENCY_CODE = CurrencyCode.parse("AUD");

    @Test
    public void testConvertNullFails() {
        this.convertFails(
            null,
            CurrencyValue.class
        );
    }

    @Test
    public void testConvertInvalidNumberToCurrencyValueFails() {
        this.convertFails(
            "Invalid!",
            CurrencyValue.class
        );
    }

    @Test
    public void testConvertInvalidCurrencyCodeNumberToCurrencyValueFails() {
        this.convertFails(
            "Invalid! 123",
            CurrencyValue.class
        );
    }

    @Test
    public void testConvertNumberInvalidCurrencyCodeToCurrencyValueFails() {
        this.convertFails(
            "123 Invalid!",
            CurrencyValue.class
        );
    }

    @Test
    public void testConvertStringWithNumberToCurrencyValue() {
        this.convertAndCheck(
            "12",
            CurrencyValue.class,
            CurrencyValue.with(
                BigDecimal.valueOf(12),
                CURRENCY_CODE
            )
        );
    }

    @Test
    public void testConvertStringWithCurrencyCodeNumberToCurrencyValue() {
        this.convertAndCheck(
            "NZD 12",
            CurrencyValue.class,
            CurrencyValue.with(
                BigDecimal.valueOf(12),
                CurrencyCode.parse("NZD")
            )
        );
    }

    @Test
    public void testConvertStringWithCurrencyCodeNumberToCurrencyValue2() {
        this.convertAndCheck(
            "NZD 12.5",
            CurrencyValue.class,
            CurrencyValue.with(
                BigDecimal.valueOf(12.5),
                CurrencyCode.parse("NZD")
            )
        );
    }

    @Test
    public void testConvertStringWithNumberCurrencyCodeToCurrencyValue() {
        this.convertAndCheck(
            "12 NZD",
            CurrencyValue.class,
            CurrencyValue.with(
                BigDecimal.valueOf(12),
                CurrencyCode.parse("NZD")
            )
        );
    }

    @Override
    public ConverterTextToCurrencyValue<ConverterContext> createConverter() {
        return ConverterTextToCurrencyValue.instance();
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
                                                 final Class<T> target) {
                return this.converter.convert(
                    value,
                    target,
                    this
                );
            }

            @Override
            public CurrencyCode currencyCode() {
                return CURRENCY_CODE;
            }

            @Override
            public Optional<Currency> currencyForLocale(final Locale locale) {
                return Optional.of(
                    Currency.getInstance(locale)
                );
            }

            @Override
            public String currencySymbol() {
                return "$";
            }

            @Override
            public char decimalSeparator() {
                return '.';
            }

            @Override
            public String exponentSymbol() {
                return "E";
            }

            @Override
            public char groupSeparator() {
                return ',';
            }

            @Override
            public char negativeSign() {
                return '-';
            }

            @Override
            public char percentSymbol() {
                return '%';
            }

            @Override
            public char positiveSign() {
                return '+';
            }

            @Override
            public Locale locale() {
                return Locale.forLanguageTag("en-AU");
            }

            private final Converter<ConverterContext> converter = Converters.collection(
                Lists.of(
                    Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString(),
                    Converters.textToCurrencyCode(),
                    Converters.textToNumber(
                        (c) -> (DecimalFormat) DecimalFormat.getInstance()
                    )
                )
            );
        };
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "text to CurrencyValue"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToCurrencyValue<ConverterContext>> type() {
        return Cast.to(ConverterTextToCurrencyValue.class);
    }
}
