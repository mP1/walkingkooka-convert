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

public final class ConverterTemporalLocalDateToNumberTest extends ConverterTemporalLocalDateTestCase<ConverterTemporalLocalDateToNumber<ConverterContext>, Number> {

    // fail(overflow)....................................................................................................

    @Test
    public void testConvertLocalDateToByteOverflowFails() {
        this.convertLocalDateToFails3(Byte.class);
    }

    @Test
    public void testConvertLocalDateToShortOverflowFails() {
        this.convertLocalDateToFails3(Byte.class);
    }

    private void convertLocalDateToFails3(final Class<? extends Number> type) {
        this.convertFails(LocalDate.MAX, type);
    }

    // pass.............................................................................................................

    private final byte BYTE_VALUE = 23;

    @Test
    public void testConvertLocalDateToBigDecimal() {
        this.convertToNumberAndCheck(
                BigDecimal.valueOf(BYTE_VALUE - DATE_OFFSET)
        );
    }

    @Test
    public void testConvertLocalDateToBigInteger() {
        this.convertToNumberAndCheck(
                BigInteger.valueOf(BYTE_VALUE - DATE_OFFSET)
        );
    }

    @Test
    public void testConvertLocalDateToByte() {
        this.convertToNumberAndCheck(
                (byte) (BYTE_VALUE - DATE_OFFSET)
        );
    }

    @Test
    public void testConvertLocalDateToShort() {
        this.convertToNumberAndCheck(
                (short) (BYTE_VALUE - DATE_OFFSET)
        );
    }

    @Test
    public void testConvertLocalDateToInteger() {
        this.convertToNumberAndCheck(
                (int) (BYTE_VALUE - DATE_OFFSET)
        );
    }

    @Test
    public void testConvertLocalDateToLong() {
        this.convertToNumberAndCheck(
                (long) (BYTE_VALUE - DATE_OFFSET)
        );
    }

    @Test
    public void testConvertLocalDateToFloat() {
        this.convertToNumberAndCheck(
                (float) (BYTE_VALUE - DATE_OFFSET)
        );
    }

    @Test
    public void testConvertLocalDateToDouble() {
        this.convertToNumberAndCheck(
                (double) (BYTE_VALUE - DATE_OFFSET)
        );
    }

    private void convertToNumberAndCheck(final Number expected) {
        this.convertAndCheck2(
                LocalDate.ofEpochDay(BYTE_VALUE),
                expected
        );
    }

    @Test
    public void testConvertLocalDateToNumber() {
        this.convertAndCheck(
                LocalDate.ofEpochDay(BYTE_VALUE),
                Number.class,
                (long) (BYTE_VALUE - DATE_OFFSET)
        );
    }

    @Override
    public ConverterTemporalLocalDateToNumber<ConverterContext> createConverter() {
        return ConverterTemporalLocalDateToNumber.instance();
    }

    @Override
    Class<Number> targetType() {
        return Number.class;
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createConverter(),
                "LocalDate to Number"
        );
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<ConverterTemporalLocalDateToNumber<ConverterContext>> type() {
        return Cast.to(ConverterTemporalLocalDateToNumber.class);
    }
}
