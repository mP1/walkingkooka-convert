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

    private final byte BYTE = 123;

    private final LocalDate BYTE_DATE = LocalDate.of(1899, 12, 30)
        .plusDays(BYTE);

    @Test
    public void testConvertLocalDateToBigDecimal() {
        this.convertAndCheck(
            BYTE_DATE,
            BigDecimal.valueOf(BYTE)
        );
    }

    @Test
    public void testConvertLocalDateToBigInteger() {
        this.convertAndCheck(
            BYTE_DATE,
            BigInteger.valueOf(BYTE)
        );
    }

    @Test
    public void testConvertLocalDateToByte() {
        this.convertAndCheck(
            BYTE_DATE,
            (byte)BYTE
        );
    }

    @Test
    public void testConvertLocalDateToShort() {
        this.convertAndCheck(
            BYTE_DATE,
            (short)BYTE
        );
    }

    @Test
    public void testConvertLocalDateToInteger() {
        this.convertAndCheck(
            BYTE_DATE,
            (int)BYTE
        );
    }

    @Test
    public void testConvertLocalDateToLong() {
        this.convertAndCheck(
            BYTE_DATE,
            (long)BYTE
        );
    }

    @Test
    public void testConvertLocalDateToFloat() {
        this.convertAndCheck(
            BYTE_DATE,
            (float)BYTE
        );
    }

    @Test
    public void testConvertLocalDateToDouble() {
        this.convertAndCheck(
            BYTE_DATE,
            (double)BYTE
        );
    }

    @Test
    public void testConvertLocalDateToNumber() {
        this.convertAndCheck(
            BYTE_DATE,
            Number.class,
            (long)BYTE
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
