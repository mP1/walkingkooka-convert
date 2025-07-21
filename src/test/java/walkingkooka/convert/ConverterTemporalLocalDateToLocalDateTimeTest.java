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

public final class ConverterTemporalLocalDateToLocalDateTimeTest extends ConverterTemporalLocalDateTestCase<ConverterTemporalLocalDateToLocalDateTime<ConverterContext>, LocalDateTime> {

    @Test
    public void testConvertLocalDateToLocalDateTime() {
        final LocalDate date = LocalDate.of(2000, 1, 31);
        this.convertAndCheck2(
            date,
            LocalDateTime.of(
                date,
                LocalTime.MIDNIGHT
            )
        );
    }

    @Test
    public void testConvertLocalDateToLocalDateTime2() {
        final LocalDate date = LocalDate.of(1999, 12, 31);
        this.convertAndCheck2(
            date,
            LocalDateTime.of(
                date,
                LocalTime.MIDNIGHT
            )
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "LocalDate to LocalDateTime"
        );
    }

    // ConverterTesting.................................................................................................

    @Override
    public ConverterTemporalLocalDateToLocalDateTime<ConverterContext> createConverter() {
        return ConverterTemporalLocalDateToLocalDateTime.instance();
    }

    @Override
    Class<LocalDateTime> targetType() {
        return LocalDateTime.class;
    }

    // ClassTesting....................................................................................................

    @Override
    public Class<ConverterTemporalLocalDateToLocalDateTime<ConverterContext>> type() {
        return Cast.to(ConverterTemporalLocalDateToLocalDateTime.class);
    }
}
