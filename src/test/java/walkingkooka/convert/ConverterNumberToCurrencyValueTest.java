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
import walkingkooka.currency.CurrencyCode;
import walkingkooka.currency.CurrencyValue;

import java.util.Currency;

public final class ConverterNumberToCurrencyValueTest extends ConverterTestCase2<ConverterNumberToCurrencyValue<ConverterContext>> {

    @Test
    public void testConvertNullFails() {
        this.convertFails(
            null,
            Currency.class
        );
    }

    private final static CurrencyCode CURRENCY_CODE = CurrencyCode.parse("AUD");

    @Test
    public void testConvertCurrencyCodeToCurrency() {
        final Number number = 123.456;

        this.convertAndCheck(
            number,
            CurrencyValue.with(
                number,
                CURRENCY_CODE
            )
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "Number to CurrencyValue"
        );
    }

    @Override
    public ConverterNumberToCurrencyValue<ConverterContext> createConverter() {
        return ConverterNumberToCurrencyValue.instance();
    }

    @Override
    public FakeConverterContext createContext() {
        return new FakeConverterContext() {
            @Override
            public CurrencyCode currencyCode() {
                return CURRENCY_CODE;
            }
        };
    }

    // class............................................................................................................

    @Override
    public Class<ConverterNumberToCurrencyValue<ConverterContext>> type() {
        return Cast.to(ConverterNumberToCurrencyValue.class);
    }
}
