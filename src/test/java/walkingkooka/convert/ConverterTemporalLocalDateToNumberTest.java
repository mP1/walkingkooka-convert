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

    private final byte BYTE_VALUE = 123;

    @Test
    public void testConvertLocalDateToBigDecimal() {
        this.convertAndCheck3(BigDecimal.valueOf(BYTE_VALUE));
    }

    @Test
    public void testConvertLocalDateToBigInteger() {
        this.convertAndCheck3(BigInteger.valueOf(BYTE_VALUE));
    }

    @Test
    public void testConvertLocalDateToByte() {
        this.convertAndCheck3(BYTE_VALUE);
    }

    @Test
    public void testConvertLocalDateToShort() {
        this.convertAndCheck3((short)BYTE_VALUE);
    }

    @Test
    public void testConvertLocalDateToInteger() {
        this.convertAndCheck3((int)BYTE_VALUE);
    }

    @Test
    public void testConvertLocalDateToLong() {
        this.convertAndCheck3((long)BYTE_VALUE);
    }

    @Test
    public void testConvertLocalDateToFloat() {
        this.convertAndCheck3((float)BYTE_VALUE);
    }

    @Test
    public void testConvertLocalDateToDouble() {
        this.convertAndCheck3((double)BYTE_VALUE);
    }

    private void convertAndCheck3(final Number expected) {
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
                (long)BYTE_VALUE
        );
    }

    @Override
    public ConverterTemporalLocalDateToNumber<ConverterContext> createConverter() {
        return ConverterTemporalLocalDateToNumber.with(Converters.JAVA_EPOCH_OFFSET);
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
