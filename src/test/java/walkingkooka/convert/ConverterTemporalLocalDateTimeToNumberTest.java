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

    private final static byte BYTE_VALUE = 12;

    @Test
    public void testConvertDateTimeToBigDecimal() {
        this.convertDateTimeToNumberAndCheck(
                BigDecimal.valueOf(BYTE_VALUE)
        );
    }

    @Test
    public void testConvertDateTimeToBigDecimalQuarter() {
        this.convertDateTimeToNumberAndCheck2(
                BigDecimal.valueOf(BYTE_VALUE).add(BigDecimal.valueOf(0.25))
        );
    }

    @Test
    public void testConvertDateTimeToBigInteger() {
        this.convertDateTimeToNumberAndCheck(BigInteger.valueOf(BYTE_VALUE));
    }

    @Test
    public void testConvertDateTimeToByte() {
        this.convertDateTimeToNumberAndCheck(BYTE_VALUE);
    }

    @Test
    public void testConvertDateTimeToShort() {
        this.convertDateTimeToNumberAndCheck((short)BYTE_VALUE);
    }

    @Test
    public void testConvertDateTimeToInteger() {
        this.convertDateTimeToNumberAndCheck((int)BYTE_VALUE);
    }

    @Test
    public void testConvertDateTimeToLong() {
        this.convertDateTimeToNumberAndCheck((long)BYTE_VALUE);
    }

    @Test
    public void testConvertDateTimeToFloat() {
        this.convertDateTimeToNumberAndCheck((float)BYTE_VALUE);
    }

    @Test
    public void testConvertDateTimeToFloatQuarter() {
        this.convertDateTimeToNumberAndCheck2(BYTE_VALUE + 0.25f);
    }

    @Test
    public void testConvertDateTimeToDouble() {
        this.convertDateTimeToNumberAndCheck((double)BYTE_VALUE);
    }

    @Test
    public void testConvertDateTimeToDoubleQuarter() {
        this.convertDateTimeToNumberAndCheck2(BYTE_VALUE + 0.25);
    }

    private void convertDateTimeToNumberAndCheck(final Number expected) {
        this.convertAndCheck2(
                LocalDateTime.of(
                        LocalDate.ofEpochDay(BYTE_VALUE), 
                        LocalTime.MIDNIGHT
                ),
                expected
        );
    }

    private void convertDateTimeToNumberAndCheck2(final Number expected) {
        this.convertAndCheck2(
                LocalDateTime.of(
                        LocalDate.ofEpochDay(BYTE_VALUE), 
                        LocalTime.of(6, 0)
                ),
                expected
        );
    }

    @Test
    public void testConvertDateTimeToNumber() {
        this.convertAndCheck(
                LocalDateTime.of(
                        LocalDate.ofEpochDay(BYTE_VALUE), 
                        LocalTime.MIDNIGHT
                ),
                Number.class,
                (double)BYTE_VALUE
        );
    }

    // withOffset.......................................................................................................

    private final static int OFFSET = 100;

    @Test
    public void testConvertDateTimeWithOffsetToBigDecimal() {
        this.convertDateTimeWithOffsetAndCheck(
                BigDecimal.valueOf(OFFSET + BYTE_VALUE)
        );
    }

    @Test
    public void testConvertDateTimeWithOffsetToBigInteger() {
        this.convertDateTimeWithOffsetAndCheck(
                BigInteger.valueOf(OFFSET + BYTE_VALUE)
        );
    }

    @Test
    public void testConvertDateTimeWithOffsetToByte() {
        this.convertDateTimeWithOffsetAndCheck(
                (byte) (OFFSET + BYTE_VALUE)
        );
    }

    @Test
    public void testConvertDateTimeWithOffsetToShort() {
        this.convertDateTimeWithOffsetAndCheck(
                (short)((byte) (OFFSET + BYTE_VALUE))
        );
    }

    @SuppressWarnings("RedundantCast")
    @Test
    public void testConvertDateTimeWithOffsetToInteger() {
        this.convertDateTimeWithOffsetAndCheck(
                (int)(OFFSET + BYTE_VALUE)
        );
    }

    @Test
    public void testConvertDateTimeWithOffsetToLong() {
        this.convertDateTimeWithOffsetAndCheck(
                (long)(OFFSET + BYTE_VALUE)
        );
    }

    @Test
    public void testConvertDateTimeWithOffsetToFloat() {
        this.convertDateTimeWithOffsetAndCheck(
                (float)(OFFSET + BYTE_VALUE)
        );
    }

    @Test
    public void testConvertDateTimeWithOffsetToDouble() {
        this.convertDateTimeWithOffsetAndCheck(
                (double)(OFFSET + BYTE_VALUE)
        );
    }

    private void convertDateTimeWithOffsetAndCheck(final Number expected) {
        this.convertAndCheck(
                ConverterTemporalLocalDateTimeToNumber.with(OFFSET),
                LocalDateTime.of(
                        LocalDate.ofEpochDay(BYTE_VALUE), 
                        LocalTime.MIDNIGHT
                ),
                Cast.to(expected.getClass()),
                expected
        );
    }

    @Test
    public void testConvertDateTimeWithOffsetToNumber() {
        this.convertAndCheck(
                ConverterTemporalLocalDateTimeToNumber.with(OFFSET),
                LocalDateTime.of(LocalDate.ofEpochDay(BYTE_VALUE), LocalTime.MIDNIGHT),
                Number.class,
                (double)(BYTE_VALUE + OFFSET)
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
        return ConverterTemporalLocalDateTimeToNumber.with(Converters.JAVA_EPOCH_OFFSET);
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
