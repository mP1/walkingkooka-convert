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

import walkingkooka.convert.ConverterContextDelegatorTest.TestConverterContextDelegator;
import walkingkooka.currency.CurrencyLocaleContextTesting;
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.math.DecimalNumberContextDelegator;

import java.math.MathContext;

public final class ConverterContextDelegatorTest implements ConverterContextTesting<TestConverterContextDelegator>,
    CurrencyLocaleContextTesting,
    DecimalNumberContextDelegator {

    @Override
    public TestConverterContextDelegator createContext() {
        return new TestConverterContextDelegator();
    }

    @Override
    public int decimalNumberDigitCount() {
        return DecimalNumberContext.DEFAULT_NUMBER_DIGIT_COUNT;
    }

    @Override
    public DecimalNumberContext decimalNumberContext() {
        return DECIMAL_NUMBER_CONTEXT;
    }

    @Override
    public MathContext mathContext() {
        return MATH_CONTEXT;
    }

    // class............................................................................................................

    @Override
    public Class<TestConverterContextDelegator> type() {
        return TestConverterContextDelegator.class;
    }

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    final static class TestConverterContextDelegator implements ConverterContextDelegator {

        @Override
        public ConverterContext converterContext() {
            return ConverterContexts.basic(
                false, // canNumbersHaveGroupSeparator
                0, // dateTimeOffset
                ',', // valueSeparator
                Converters.fake(),
                BinaryNumberConverterFunctions.multiply(),
                BINARY_TEXT_CONTEXT,
                CURRENCY_LOCALE_CONTEXT,
                DATE_TIME_CONTEXT,
                DECIMAL_NUMBER_CONTEXT
            );
        }

        @Override
        public String toString() {
            return this.converterContext().toString();
        }
    }
}
