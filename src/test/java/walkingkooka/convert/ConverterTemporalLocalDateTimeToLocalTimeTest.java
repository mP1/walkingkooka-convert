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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public final class ConverterTemporalLocalDateTimeToLocalTimeTest extends ConverterTemporalLocalDateTimeTestCase<ConverterTemporalLocalDateTimeToLocalTime<ConverterContext>, LocalTime> {

    @Test
    public void testConvertMidnightTime() {
        final LocalTime time = LocalTime.MIDNIGHT;
        this.convertAndCheck2(
                LocalDateTime.of(
                        date(),
                        time
                ),
                time
        );
    }

    @Test
    public void testConvertNonMidnightTime2() {
        final LocalTime time = LocalTime.of(12, 58, 59);
        this.convertAndCheck2(
                LocalDateTime.of(
                        date(),
                        time
                ),
                time
        );
    }

    private LocalDate date() {
        return LocalDate.of(2000, 1, 31);
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createConverter(), "LocalDateTime->LocalTime");
    }

    // ConverterTesting.................................................................................................

    @Override
    public ConverterTemporalLocalDateTimeToLocalTime<ConverterContext> createConverter() {
        return ConverterTemporalLocalDateTimeToLocalTime.instance();
    }

    @Override
    Class<LocalTime> targetType() {
        return LocalTime.class;
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<ConverterTemporalLocalDateTimeToLocalTime<ConverterContext>> type() {
        return Cast.to(ConverterTemporalLocalDateTimeToLocalTime.class);
    }
}
