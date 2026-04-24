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
import walkingkooka.ValueTesting;
import walkingkooka.collect.list.Lists;
import walkingkooka.currency.CurrencyCode;

import java.util.Currency;
import java.util.Optional;

public final class ConverterTextToCurrencyTest extends ConverterTextToTestCase<ConverterTextToCurrency<ConverterContext>, Currency>
    implements ValueTesting {

    @Test
    public void testConvertNullFails() {
        this.convertFails(
            null,
            Currency.class
        );
    }

    @Test
    public void testConvertInvalidStringToCurrencyFails() {
        this.convertFails(
            "Invalid!",
            Currency.class
        );
    }

    @Test
    public void testConvertStringAudToCurrency() {
        this.convertAndCheck(
            "AUD",
            Currency.class,
            Currency.getInstance("AUD")
        );
    }

    @Override
    public ConverterTextToCurrency<ConverterContext> createConverter() {
        return ConverterTextToCurrency.instance();
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
            public Optional<Currency> currencyForCurrencyCode(final CurrencyCode currencyCode) {
                valueAndCheck(
                    currencyCode,
                    "AUD"
                );
                return Optional.of(
                    Currency.getInstance(
                        currencyCode.value()
                    )
                );
            }

            private final Converter<ConverterContext> converter = Converters.collection(
                Lists.of(
                    Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString(),
                    Converters.textToCurrencyCode()
                )
            );
        };
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "text to Currency"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToCurrency<ConverterContext>> type() {
        return Cast.to(ConverterTextToCurrency.class);
    }
}
