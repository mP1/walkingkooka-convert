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

public final class ConverterNumberToLocalDateTimeTest extends ConverterNumberTestCase<ConverterNumberToLocalDateTime<ConverterContext>, LocalDateTime> {

    private final static int VALUE = 123;
    private final static LocalTime MIDNIGHT = LocalTime.ofSecondOfDay(0);
    private final static LocalDateTime DATE_TIME_EXCEL_OFFSET = LocalDateTime.of(1900, 5, 2, 0, 0, 0);

    @Test
    public void testConvertNonNumberTypeFails() {
        this.convertFails2("fail!");
    }

    @Test
    public void testConvertFromLocalDateTimeFails() {
        this.convertFails2(LocalDateTime.of(1, 2, 3, 4, 5));
    }

    @Test
    public void testConvertBigDecimal() {
        this.convertAndCheck2(BigDecimal.valueOf(123));
    }

    @Test
    public void testConvertBigDecimalWithFraction() {
        this.convertAndCheck(BigDecimal.valueOf(123.5), this.localDateTime(VALUE, 12, 0));
    }

    @Test
    public void testConvertBigDecimalWithExcelOffset() {
        this.convertAndCheckExcelOffset(BigDecimal.valueOf(VALUE));
    }

    @Test
    public void testConvertBigInteger() {
        this.convertAndCheck2(BigInteger.valueOf(VALUE));
    }

    @Test
    public void testConvertBigIntegerWithExcelOffset() {
        this.convertAndCheckExcelOffset(BigInteger.valueOf(VALUE));
    }

    @Test
    public void testConvertFloat() {
        this.convertAndCheck2(123.0f);
    }

    @Test
    public void testConvertDouble() {
        this.convertAndCheck2(123.0);
    }

    @Test
    public void testConvertDoubleWithFraction() {
        this.convertAndCheck(BigDecimal.valueOf(123.5), this.localDateTime(VALUE, 12, 0));
    }

    @Test
    public void testConvertDoubleWithExcelOffset() {
        this.convertAndCheckExcelOffset(BigDecimal.valueOf(VALUE));
    }

    @Override
    public void testConvertDoubleMaxFails() {
        throw new UnsupportedOperationException();
    }

    @Test
    public void testConvertByte() {
        this.convertAndCheck2((byte) 123);
    }

    @Test
    public void testConvertShort() {
        this.convertAndCheck2((short) 123);
    }

    @Test
    public void testConvertInteger() {
        this.convertAndCheck2(123);
    }

    @Test
    public void testConvertLong() {
        this.convertAndCheck2((long)VALUE);
    }

    @Test
    public void testLongWithExcelOffset() {
        this.convertAndCheckExcelOffset((long)VALUE);
    }

    private void convertAndCheck2(final Object value) {
        this.convertAndCheck(value, this.localDateTime(VALUE, MIDNIGHT));
    }

    private void convertAndCheckExcelOffset(final Number value) {
        this.convertAndCheck(ConverterNumberToLocalDateTime.with(Converters.EXCEL_1900_DATE_SYSTEM_OFFSET),
                value,
                LocalDateTime.class,
                DATE_TIME_EXCEL_OFFSET);
    }

    @Override
    public ConverterNumberToLocalDateTime<ConverterContext> createConverter() {
        return ConverterNumberToLocalDateTime.with(Converters.JAVA_EPOCH_OFFSET);
    }

    private LocalDateTime localDateTime(final int date, final int hours, final int minutes) {
        return this.localDateTime(date, LocalTime.of(hours, minutes));
    }

    private LocalDateTime localDateTime(final int date, final LocalTime time) {
        return this.localDateTime(LocalDate.ofEpochDay(date), time);
    }

    private LocalDateTime localDateTime(final LocalDate date, final LocalTime time) {
        return LocalDateTime.of(date, time);
    }

    @Override
    protected Class<LocalDateTime> targetType() {
        return LocalDateTime.class;
    }

    @Override
    public Class<ConverterNumberToLocalDateTime<ConverterContext>> type() {
        return Cast.to(ConverterNumberToLocalDateTime.class);
    }
}
