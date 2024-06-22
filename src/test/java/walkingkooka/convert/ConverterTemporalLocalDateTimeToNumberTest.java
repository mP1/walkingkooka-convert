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
    public void testToByteOverflowFails() {
        this.convertFails3(Byte.class);
    }

    @Test
    public void testToShortOverflowFails() {
        this.convertFails3(Byte.class);
    }

    private void convertFails3(final Class<? extends Number> type) {
        this.convertFails(LocalDateTime.MAX, type);
    }

    // pass.............................................................................................................

    private final static byte BYTE_VALUE = 12;

    @Test
    public void testToBigDecimal() {
        this.convertAndCheck3(BigDecimal.valueOf(BYTE_VALUE));
    }

    @Test
    public void testToBigDecimalQuarter() {
        this.convertAndCheck4(BigDecimal.valueOf(BYTE_VALUE).add(BigDecimal.valueOf(0.25)));
    }

    @Test
    public void testToBigInteger() {
        this.convertAndCheck3(BigInteger.valueOf(BYTE_VALUE));
    }

    @Test
    public void testToByte() {
        this.convertAndCheck3(BYTE_VALUE);
    }

    @Test
    public void testToShort() {
        this.convertAndCheck3((short)BYTE_VALUE);
    }

    @Test
    public void testToInteger() {
        this.convertAndCheck3((int)BYTE_VALUE);
    }

    @Test
    public void testToLong() {
        this.convertAndCheck3((long)BYTE_VALUE);
    }

    @Test
    public void testToFloat() {
        this.convertAndCheck3((float)BYTE_VALUE);
    }

    @Test
    public void testToFloatQuarter() {
        this.convertAndCheck4(BYTE_VALUE + 0.25f);
    }

    @Test
    public void testToDouble() {
        this.convertAndCheck3((double)BYTE_VALUE);
    }

    @Test
    public void testToDoubleQuarter() {
        this.convertAndCheck4(BYTE_VALUE + 0.25);
    }

    private void convertAndCheck3(final Number expected) {
        this.convertAndCheck2(LocalDateTime.of(LocalDate.ofEpochDay(BYTE_VALUE), LocalTime.MIDNIGHT),
                expected);
    }

    private void convertAndCheck4(final Number expected) {
        this.convertAndCheck2(LocalDateTime.of(LocalDate.ofEpochDay(BYTE_VALUE), LocalTime.of(6, 0)),
                expected);
    }

    @Test
    public void testToNumber() {
        this.convertAndCheck(LocalDateTime.of(LocalDate.ofEpochDay(BYTE_VALUE), LocalTime.MIDNIGHT),
                Number.class,
                (double)BYTE_VALUE);
    }

    // withOffset.......................................................................................................

    private final static int OFFSET = 100;

    @Test
    public void testWithOffsetToBigDecimal() {
        this.convertWithOffsetAndCheck3(BigDecimal.valueOf(OFFSET + BYTE_VALUE));
    }

    @Test
    public void testWithOffsetToBigInteger() {
        this.convertWithOffsetAndCheck3(BigInteger.valueOf(OFFSET + BYTE_VALUE));
    }

    @Test
    public void testWithOffsetToByte() {
        this.convertWithOffsetAndCheck3((byte) (OFFSET + BYTE_VALUE));
    }

    @Test
    public void testWithOffsetToShort() {
        this.convertWithOffsetAndCheck3((short)((byte) (OFFSET + BYTE_VALUE)));
    }

    @SuppressWarnings("RedundantCast")
    @Test
    public void testWithOffsetToInteger() {
        this.convertWithOffsetAndCheck3((int)(OFFSET + BYTE_VALUE));
    }

    @Test
    public void testWithOffsetToLong() {
        this.convertWithOffsetAndCheck3((long)(OFFSET + BYTE_VALUE));
    }

    @Test
    public void testWithOffsetToFloat() {
        this.convertWithOffsetAndCheck3((float)(OFFSET + BYTE_VALUE));
    }

    @Test
    public void testWithOffsetToDouble() {
        this.convertWithOffsetAndCheck3((double)(OFFSET + BYTE_VALUE));
    }

    private void convertWithOffsetAndCheck3(final Number expected) {
        this.convertAndCheck(ConverterTemporalLocalDateTimeToNumber.with(OFFSET),
                LocalDateTime.of(LocalDate.ofEpochDay(BYTE_VALUE), LocalTime.MIDNIGHT),
                Cast.to(expected.getClass()),
                expected);
    }

    @Test
    public void testWithOffsetToNumber() {
        this.convertAndCheck(ConverterTemporalLocalDateTimeToNumber.with(OFFSET),
                LocalDateTime.of(LocalDate.ofEpochDay(BYTE_VALUE), LocalTime.MIDNIGHT),
                Number.class,
                (double)(BYTE_VALUE + OFFSET));
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createConverter(), "LocalDateTime->Number");
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
