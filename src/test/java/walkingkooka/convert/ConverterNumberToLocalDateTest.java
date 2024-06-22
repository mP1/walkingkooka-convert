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

public final class ConverterNumberToLocalDateTest extends ConverterNumberTestCase<ConverterNumberToLocalDate<ConverterContext>, LocalDate> {

    private final static byte VALUE = 123;
    private final static LocalDate DATE_VALUE = LocalDate.ofEpochDay(VALUE);
    private final static LocalDate DATE_VALUE_EXCEL_OFFSET = LocalDate.of(1900, 5, 2);

    @Test
    public void testNonNumberTypeFails() {
        this.convertFails2("fail!");
    }

    @Test
    public void testFromLocalDateFails() {
        this.convertFails2(LocalDate.ofEpochDay(VALUE));
    }

    @Test
    public void testBigDecimal() {
        this.convertAndCheck2(BigDecimal.valueOf(VALUE));
    }

    @Test
    public void testBigDecimalWithFraction() {
        this.convertFails2(BigDecimal.valueOf(123.5));
    }

    @Test
    public void testBigDecimalWithExcelOffset() {
        this.convertAndCheckExcelOffset(BigDecimal.valueOf(VALUE));
    }

    @Test
    public void testBigInteger() {
        this.convertAndCheck2(BigInteger.valueOf(VALUE));
    }

    @Test
    public void testBigIntegerWithExcelOffset() {
        this.convertAndCheckExcelOffset(BigInteger.valueOf(VALUE));
    }

    @Test
    public void testByte() {
        this.convertAndCheck2(VALUE);
    }

    @Test
    public void testShort() {
        this.convertAndCheck2((short)VALUE);
    }

    @Test
    public void testInteger() {
        this.convertAndCheck2((int)VALUE);
    }

    @Test
    public void testLong() {
        this.convertAndCheck((long)VALUE, LocalDate.ofEpochDay(VALUE));
    }

    @Test
    public void testLongWithExcelOffset() {
        this.convertAndCheckExcelOffset((long)VALUE);
    }

    @Test
    public void testFloat() {
        this.convertAndCheck2((float)VALUE);
    }

    @Test
    public void testDouble() {
        this.convertAndCheck2((double)VALUE);
    }

    @Test
    public void testDoubleWithFraction() {
        this.convertFails2(123.75);
    }

    @Test
    public void testDoubleWithExcelOffset() {
        this.convertAndCheckExcelOffset((double)VALUE);
    }

    @Override
    public void testDoubleMaxFails() {
        throw new UnsupportedOperationException();
    }

    private void convertAndCheck2(final Number value) {
        this.convertAndCheck(value, DATE_VALUE);
    }

    private void convertAndCheckExcelOffset(final Number value) {
        this.convertAndCheck(ConverterNumberToLocalDate.with(Converters.EXCEL_1900_DATE_SYSTEM_OFFSET),
                value,
                LocalDate.class,
                DATE_VALUE_EXCEL_OFFSET);
    }

    @Override
    public ConverterNumberToLocalDate<ConverterContext> createConverter() {
        return ConverterNumberToLocalDate.with(Converters.JAVA_EPOCH_OFFSET);
    }

    @Override
    protected Class<LocalDate> targetType() {
        return LocalDate.class;
    }

    @Override
    public Class<ConverterNumberToLocalDate<ConverterContext>> type() {
        return Cast.to(ConverterNumberToLocalDate.class);
    }
}
