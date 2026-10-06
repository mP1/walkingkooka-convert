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
import walkingkooka.currency.HasCurrency;
import walkingkooka.currency.HasCurrencyTesting;
import walkingkooka.currency.HasOptionalCurrency;

import java.util.Currency;
import java.util.Optional;

public final class ConverterToCurrencyTest extends ConverterTestCase2<ConverterToCurrency<ConverterContext>>
    implements HasCurrencyTesting {

    @Test
    public void testConvertStringToCurrencyFails() {
        this.convertFails(
            CURRENCY.getCurrencyCode(),
            Currency.class
        );
    }

    @Test
    public void testConvertNullToCurrency() {
        this.convertAndCheck(
            null,
            Currency.class
        );
    }

    @Test
    public void testConvertCurrencyToCurrency() {
        this.convertAndCheck(
            CURRENCY,
            CURRENCY
        );
    }

    @Test
    public void testConvertHasCurrencyToCurrency() {
        this.convertAndCheck(
            new HasCurrency() {
                @Override
                public Currency currency() {
                    return CURRENCY;
                }
            },
            CURRENCY
        );
    }

    @Test
    public void testConvertHasOptionalCurrencyToCurrency() {
        this.convertAndCheck(
            new HasOptionalCurrency() {
                @Override
                public Optional<Currency> currency() {
                    return Optional.of(CURRENCY);
                }
            },
            CURRENCY
        );
    }

    @Test
    public void testConvertEmptyHasOptionalCurrencyToCurrency() {
        this.convertAndCheck(
            new HasOptionalCurrency() {
                @Override
                public Optional<Currency> currency() {
                    return Optional.empty();
                }
            },
            Currency.class,
            null // expected
        );
    }

    @Override
    public ConverterToCurrency<ConverterContext> createConverter() {
        return ConverterToCurrency.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "to Currency"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterToCurrency<ConverterContext>> type() {
        return Cast.to(ConverterToCurrency.class);
    }
}
