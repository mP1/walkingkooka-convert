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
import walkingkooka.convert.CanConvertDelegatorTest.TestCanConvertDelegator;
import walkingkooka.datetime.DateTimeContexts;
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.math.DecimalNumberContexts;

import java.math.MathContext;
import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class CanConvertDelegatorTest implements CanConvertTesting<TestCanConvertDelegator> {

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
    public TestCanConvertDelegator createCanConvert() {
        return new TestCanConvertDelegator();
    }

    static class TestCanConvertDelegator implements CanConvertDelegator {
        @Override
        public CanConvert canConvert() {
            final Locale locale = Locale.forLanguageTag("EN-AU");

            return ConverterContexts.basic(
                    Converters.EXCEL_1900_DATE_SYSTEM_OFFSET,
                    Converters.stringToLocalDate(
                            (x) -> DateTimeFormatter.ofPattern("yyyy MM dd")
                    ),
                    DateTimeContexts.basic(
                            DateTimeSymbols.fromDateFormatSymbols(
                                    new DateFormatSymbols(locale)
                            ),
                            locale,
                            1950, // defaultYear
                            50, // twoDigitYear
                            LocalDateTime::now
                    ),
                    DecimalNumberContexts.american(MathContext.DECIMAL32)
            );
        }
    }
}
