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
import walkingkooka.currency.CurrencyContextTesting;
import walkingkooka.currency.CurrencyValue;
import walkingkooka.currency.HasCurrencyCodeTesting;
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.util.HasLocaleTesting;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

public final class ConverterToCurrencyValueTest extends ConverterTestCase2<ConverterToCurrencyValue<ConverterContext>>
    implements HasCurrencyCodeTesting,
    CurrencyContextTesting,
    HasLocaleTesting {

    @Test
    public void testConvertStringToNumberFails() {
        this.convertFails(
            "$1",
            Number.class
        );
    }

    @Test
    public void testConvertNullToCurrencyValueFails() {
        this.convertFails(
            null,
            CurrencyValue.class
        );
    }

    @Test
    public void testConvertStringToCurrencyValue() {
        this.convertAndCheck(
            "12.5",
            CurrencyValue.class,
            CurrencyValue.with(
                new BigDecimal("12.5"),
                CURRENCY_CODE
            )
        );
    }

    @Override
    public ConverterToCurrencyValue<ConverterContext> createConverter() {
        return ConverterToCurrencyValue.instance();
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

            private final Converter<FakeConverterContext> converter = Converters.collection(
                Lists.of(
                    Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString(),
                    Converters.textToNumber(
                        (final DecimalNumberContext decimalNumberContext) -> (DecimalFormat) DecimalFormat.getInstance(LOCALE)
                    )
                )
            );

            @Override
            public CurrencyCode currencyCode() {
                return CURRENCY_CODE;
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
            public Optional<Currency> currencyForLocale(final Locale locale) {
                return CURRENCY_CONTEXT.currencyForLocale(locale);
            }

            @Override
            public Locale locale() {
                return LOCALE;
            }
        };
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "to CurrencyValue"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterToCurrencyValue<ConverterContext>> type() {
        return Cast.to(ConverterToCurrencyValue.class);
    }
}
