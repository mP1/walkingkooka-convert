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
import walkingkooka.convert.ConverterLikeDelegatorTest.TestConverterLikeDelegator;
import walkingkooka.currency.CurrencyLocaleContextTesting;
import walkingkooka.currency.CurrencyLocaleContexts;
import walkingkooka.datetime.DateTimeContextTesting;
import walkingkooka.math.DecimalNumberContextTesting;
import walkingkooka.text.BinaryTextContextTesting;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class ConverterLikeDelegatorTest implements ConverterLikeTesting<TestConverterLikeDelegator>,
    BinaryTextContextTesting,
    CurrencyLocaleContextTesting,
    DateTimeContextTesting,
    DecimalNumberContextTesting {

    @Test
    public void testConvert() {
        this.convertAndCheck(
            "1999 12 31",
            LocalDate.class,
            LocalDate.of(
                1999,
                12,
                31
            )
        );
    }

    @Override
    public TestConverterLikeDelegator createConverterLike() {
        return new TestConverterLikeDelegator();
    }

    static class TestConverterLikeDelegator implements ConverterLikeDelegator {
        @Override
        public ConverterLike converterLike() {
            return ConverterContexts.basic(
                false, // canNumbersHaveGroupSeparator
                Converters.EXCEL_1900_DATE_SYSTEM_OFFSET,
                ',', // valueSeparator
                Converters.textToLocalDate(
                    (x) -> DateTimeFormatter.ofPattern("yyyy MM dd")
                ),
                BinaryNumberConverterFunctions.fake(),
                BINARY_TEXT_CONTEXT,
                CurrencyLocaleContexts.fake(),
                DATE_TIME_CONTEXT,
                DECIMAL_NUMBER_CONTEXT
            );
        }
    }
}
