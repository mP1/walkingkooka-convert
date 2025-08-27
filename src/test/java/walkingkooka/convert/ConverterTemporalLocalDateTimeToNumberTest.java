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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public final class ConverterTemporalLocalDateTimeToNumberTest extends ConverterTemporalLocalDateTimeTestCase<ConverterTemporalLocalDateTimeToNumber<ConverterContext>, Number> {

    // fail(overflow)....................................................................................................

    @Test
    public void testConvertDateTimeToByteOverflowFails() {
        this.convertFails3(Byte.class);
    }

    @Test
    public void testConvertDateTimeToShortOverflowFails() {
        this.convertFails3(Byte.class);
    }

    private void convertFails3(final Class<? extends Number> type) {
        this.convertFails(LocalDateTime.MAX, type);
    }

    // pass.............................................................................................................

    private final static byte BYTE = 12;

    private final static LocalDateTime BYTE_DATE_TIME = LocalDateTime.of(
        LocalDate.of(1899, 12, 30)
            .plusDays(12),
        LocalTime.MIDNIGHT
    );

    private final static float FLOAT = BYTE + 0.5f;

    private final static LocalDateTime FLOAT_DATE_TIME = LocalDateTime.of(
        LocalDate.of(1899, 12, 30)
            .plusDays(12),
        LocalTime.NOON
    );

    @Test
    public void testConvertDateTimeToBigDecimal() {
        this.convertAndCheck(
            FLOAT_DATE_TIME,
            BigDecimal.valueOf(FLOAT)
        );
    }

    @Test
    public void testConvertDateTimeToBigInteger() {
        this.convertAndCheck(
            BYTE_DATE_TIME,
            BigInteger.valueOf(BYTE)
        );
    }

    @Test
    public void testConvertDateTimeToByte() {
        this.convertAndCheck(
            BYTE_DATE_TIME,
            BYTE
        );
    }

    @Test
    public void testConvertDateTimeToShort() {
        this.convertAndCheck(
            BYTE_DATE_TIME,
            (short)BYTE
        );
    }

    @Test
    public void testConvertDateTimeToInteger() {
        this.convertAndCheck(
            BYTE_DATE_TIME,
            (int)BYTE
        );
    }

    @Test
    public void testConvertDateTimeToLong() {
        this.convertAndCheck(
            BYTE_DATE_TIME,
            (long)BYTE
        );
    }

    @Test
    public void testConvertDateTimeToFloat() {
        this.convertAndCheck(
            FLOAT_DATE_TIME,
            FLOAT
        );
    }

    @Test
    public void testConvertDateTimeToDouble() {
        this.convertAndCheck(
            FLOAT_DATE_TIME,
            (double)FLOAT
        );
    }

    @Test
    public void testConvertDateTimeToNumber() {
        this.convertAndCheck(
            FLOAT_DATE_TIME,
            Number.class,
            (float) FLOAT
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "LocalDateTime to Number"
        );
    }

    // ConverterTesting.................................................................................................

    @Override
    public ConverterTemporalLocalDateTimeToNumber<ConverterContext> createConverter() {
        return ConverterTemporalLocalDateTimeToNumber.instance();
    }

    @Override
    Class<Number> targetType() {
        return Number.class;
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<ConverterTemporalLocalDateTimeToNumber<ConverterContext>> type() {
        return Cast.to(ConverterTemporalLocalDateTimeToNumber.class);
    }
}
