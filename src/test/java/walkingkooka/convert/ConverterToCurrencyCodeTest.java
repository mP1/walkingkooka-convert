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
import walkingkooka.currency.HasCurrencyCode;
import walkingkooka.currency.HasCurrencyCodeTesting;
import walkingkooka.currency.HasOptionalCurrencyCode;

import java.util.Optional;

public final class ConverterToCurrencyCodeTest extends ConverterTestCase2<ConverterToCurrencyCode<ConverterContext>>
    implements HasCurrencyCodeTesting {

    @Test
    public void testConvertStringToCurrencyCodeFails() {
        this.convertFails(
            CURRENCY_CODE.value(),
            CurrencyCode.class
        );
    }

    @Test
    public void testConvertNullToCurrencyCode() {
        this.convertAndCheck(
            null,
            CurrencyCode.class
        );
    }

    @Test
    public void testConvertCurrencyCodeToCurrencyCode() {
        this.convertAndCheck(
            CURRENCY_CODE,
            CURRENCY_CODE
        );
    }

    @Test
    public void testConvertHasCurrencyCodeToCurrencyCode() {
        this.convertAndCheck(
            new HasCurrencyCode() {
                @Override
                public CurrencyCode currencyCode() {
                    return CURRENCY_CODE;
                }
            },
            CURRENCY_CODE
        );
    }

    @Test
    public void testConvertHasOptionalCurrencyCodeToCurrencyCode() {
        this.convertAndCheck(
            new HasOptionalCurrencyCode() {
                @Override
                public Optional<CurrencyCode> currencyCode() {
                    return Optional.of(CURRENCY_CODE);
                }
            },
            CURRENCY_CODE
        );
    }

    @Test
    public void testConvertEmptyHasOptionalCurrencyCodeToCurrencyCode() {
        this.convertAndCheck(
            new HasOptionalCurrencyCode() {
                @Override
                public Optional<CurrencyCode> currencyCode() {
                    return Optional.empty();
                }
            },
            CurrencyCode.class,
            null // expected
        );
    }

    @Override
    public ConverterToCurrencyCode<ConverterContext> createConverter() {
        return ConverterToCurrencyCode.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "toCurrencyCode"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterToCurrencyCode<ConverterContext>> type() {
        return Cast.to(ConverterToCurrencyCode.class);
    }
}
