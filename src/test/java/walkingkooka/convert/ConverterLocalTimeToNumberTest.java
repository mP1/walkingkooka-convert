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
import java.time.LocalTime;

public final class ConverterLocalTimeToNumberTest extends ConverterLocalTimeTestCase<ConverterLocalTimeToNumber<ConverterContext>> {

    private final static byte BYTE = 0;
    private final static LocalTime BYTE_TIME = LocalTime.MIDNIGHT;

    private final static double DOUBLE = 0.5;
    private final static LocalTime DOUBLE_TIME = LocalTime.NOON;

    @Test
    public void testConvertNull() {
        this.convertAndCheck(
            null,
            Number.class
        );
    }

    @Test
    public void testConvertLocalTimeToBigDecimal() {
        this.convertAndCheck(
            BYTE_TIME,
            BigDecimal.valueOf(BYTE)
        );
    }

    @Test
    public void testConvertLocalTimeToBigDecimal2() {
        this.convertAndCheck(
            DOUBLE_TIME,
            BigDecimal.valueOf(DOUBLE)
        );
    }

    @Test
    public void testConvertLocalTimeToBigInteger() {
        this.convertAndCheck(
            BYTE_TIME,
            BigInteger.valueOf(BYTE)
        );
    }

    @Test
    public void testConvertLocalTimeToBigIntegerFails() {
        this.convertFails(
            DOUBLE_TIME,
            BigInteger.class
        );
    }

    @Test
    public void testConvertLocalTimeToByte() {
        this.convertAndCheck(
            BYTE_TIME,
            BYTE
        );
    }

    @Test
    public void testConvertLocalTimeToByteNonZero() {
        this.convertAndCheck(
            DOUBLE_TIME,
            Byte.class,
            (byte)0
        );
    }

    @Test
    public void testConvertLocalTimeToShort() {
        this.convertAndCheck(
            BYTE_TIME,
            (short)BYTE
        );
    }

    @Test
    public void testConvertLocalTimeToShortNonZero() {
        this.convertAndCheck(
            DOUBLE_TIME,
            Short.class,
            (short)0
        );
    }

    @Test
    public void testConvertLocalTimeToInteger() {
        this.convertAndCheck(
            BYTE_TIME,
            (int)BYTE
        );
    }

    @Test
    public void testConvertLocalTimeToIntegerNonZero() {
        this.convertAndCheck(
            DOUBLE_TIME,
            Integer.class,
            (int)0
        );
    }

    @Test
    public void testConvertLocalTimeToLong() {
        this.convertAndCheck(
            BYTE_TIME,
            (long)BYTE
        );
    }

    @Test
    public void testConvertLocalTimeToLongNonZero() {
        this.convertAndCheck(
            DOUBLE_TIME,
            Long.class,
            0L
        );
    }

    @Test
    public void testConvertLocalTimeToFloat() {
        this.convertAndCheck(
            DOUBLE_TIME,
            Float.class,
            (float)DOUBLE
        );
    }

    @Test
    public void testConvertLocalTimeToDouble() {
        this.convertAndCheck(
            DOUBLE_TIME,
            Double.class,
            (double)DOUBLE
        );
    }
    
    @Override
    public ConverterLocalTimeToNumber<ConverterContext> createConverter() {
        return ConverterLocalTimeToNumber.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "LocalTime to Number"
        );
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<ConverterLocalTimeToNumber<ConverterContext>> type() {
        return Cast.to(ConverterLocalTimeToNumber.class);
    }

    // TypeNameTesting..................................................................................................

    @Override
    public String typeNameSuffix() {
        return Number.class.getSimpleName();
    }
}
