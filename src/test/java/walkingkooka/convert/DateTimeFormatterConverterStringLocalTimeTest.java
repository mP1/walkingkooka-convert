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
import walkingkooka.datetime.DateTimeContext;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

public final class DateTimeFormatterConverterStringLocalTimeTest extends DateTimeFormatterConverterTestCase2<DateTimeFormatterConverterStringLocalTime<ConverterContext>, String, LocalTime> {

    @Test
    public void testConvert2() {
        this.convertAndCheck(this.createConverter(DateTimeFormatter.ofPattern("ss HH mm")),
                "59 12 58",
                LocalTime.class,
                LocalTime.of(12, 58, 59));
    }

    @Test
    public void testLocaleChange() {
        final DateTimeFormatterConverterStringLocalTime<ConverterContext> converter = this.createConverter();

        this.convertAndCheck2(converter,
                this.source(),
                this.createContext(),
                this.converted());

        this.convertAndCheck2(converter,
                "nachm. 59 58 12",
                this.createContext2(),
                this.converted());
    }

    @Override
    protected DateTimeFormatterConverterStringLocalTime<ConverterContext> createConverter(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return DateTimeFormatterConverterStringLocalTime.with(formatter);
    }

    @Override
    DateTimeFormatter formatter() {
        return DateTimeFormatter.ofPattern("a ss mm HH");
    }

    @Override
    protected Class<LocalTime> targetType() {
        return LocalTime.class;
    }

    @Override
    String source() {
        return "PM 59 58 12";
    }

    @Override
    LocalTime converted() {
        return LocalTime.of(12, 58, 59);
    }

    @Override
    public Class<DateTimeFormatterConverterStringLocalTime<ConverterContext>> type() {
        return Cast.to(DateTimeFormatterConverterStringLocalTime.class);
    }
}
