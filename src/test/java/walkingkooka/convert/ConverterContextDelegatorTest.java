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
import walkingkooka.datetime.DateTimeContexts;
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.math.DecimalNumberContexts;

import java.math.MathContext;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.util.Locale;

public final class ConverterContextDelegatorTest implements ConverterContextTesting<TestConverterContextDelegator> {

    @Override
    public TestConverterContextDelegator createContext() {
        return new TestConverterContextDelegator();
    }

    @Override
    public String currencySymbol() {
        return new TestConverterContextDelegator()
                .currencySymbol();
    }

    @Override
    public char decimalSeparator() {
        return new TestConverterContextDelegator()
                .decimalSeparator();
    }

    @Override
    public String exponentSymbol() {
        return new TestConverterContextDelegator()
                .exponentSymbol();
    }

    @Override
    public char groupSeparator() {
        return new TestConverterContextDelegator()
                .groupSeparator();
    }

    @Override
    public MathContext mathContext() {
        return new TestConverterContextDelegator()
                .mathContext();
    }

    @Override
    public char negativeSign() {
        return new TestConverterContextDelegator()
                .negativeSign();
    }

    @Override
    public char percentSymbol() {
        return new TestConverterContextDelegator()
                .percentSymbol();
    }

    @Override
    public char positiveSign() {
        return new TestConverterContextDelegator()
                .positiveSign();
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
            final Locale locale = Locale.ENGLISH;

            return ConverterContexts.basic(
                    0,
                    Converters.fake(),
                    DateTimeContexts.basic(
                            DateTimeSymbols.fromDateFormatSymbols(
                                    new DateFormatSymbols(locale)
                            ),
                            locale,
                            1900,
                            50,
                            LocalDateTime::now
                    ),
                    DecimalNumberContexts.american(MathContext.DECIMAL32)
            );
        }

        @Override
        public String toString() {
            return this.converterContext().toString();
        }
    }
}
