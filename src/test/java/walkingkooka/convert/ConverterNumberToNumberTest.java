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

public final class ConverterNumberToNumberTest extends ConverterTestCase2<ConverterNumberToNumber<ConverterContext>> {

    private final static Byte VALUE = 123;

    private static final BigDecimal BIG_DECIMAL = BigDecimal.valueOf(VALUE);

    private static final BigInteger BIG_INTEGER = BigInteger.valueOf(VALUE);

    private static final byte BYTE = VALUE.byteValue();

    private static final double DOUBLE = VALUE.doubleValue();

    private static final float FLOAT = VALUE.floatValue();

    private static final int INT = VALUE.intValue();

    private static final long LONG = VALUE.longValue();

    private static final short SHORT = VALUE.shortValue();

    private static final BigDecimal BIG_DECIMAL_DOUBLE_MAX2 = BigDecimal.valueOf(Double.MAX_VALUE)
        .multiply(
            BigDecimal.valueOf(Double.MAX_VALUE)
        );

    private static final BigInteger BIG_INTEGER_LONG_MAX2 = BigInteger.valueOf(Long.MAX_VALUE)
        .multiply(
            BigInteger.valueOf(Long.MAX_VALUE)
        );

    @Test
    public void testConvertNullToNumber() {
        this.convertAndCheck(
            null,
            Number.class,
            (Number)null
        );
    }

    // to Number........................................................................................................

    @Test
    public void testConvertBigDecimalToNumber() {
        this.convertToNumberAndCheck(BIG_DECIMAL);
    }

    @Test
    public void testConvertBigIntegerToNumber() {
        this.convertToNumberAndCheck(BIG_INTEGER);
    }

    @Test
    public void testConvertByteToNumber() {
        this.convertToNumberAndCheck(BYTE);
    }

    @Test
    public void testConvertDoubleToNumber() {
        this.convertToNumberAndCheck(DOUBLE);
    }

    @Test
    public void testConvertFloatToNumber() {
        this.convertToNumberAndCheck(FLOAT);
    }

    @Test
    public void testConvertIntegerToNumber() {
        this.convertToNumberAndCheck(INT);
    }

    @Test
    public void testConvertLongToNumber() {
        this.convertToNumberAndCheck(LONG);
    }

    @Test
    public void testConvertShortToNumber() {
        this.convertToNumberAndCheck(SHORT);
    }

    private void convertToNumberAndCheck(final Number number) {
        this.convertAndCheck(
            number,
            Number.class,
            number
        );
    }

    // to same type.....................................................................................................

    @Test
    public void testConvertBigDecimalToBigDecimal() {
        this.convertToSameTypeAndCheck(BIG_DECIMAL);
    }

    @Test
    public void testConvertBigIntegerToBigInteger() {
        this.convertToSameTypeAndCheck(BIG_INTEGER);
    }

    @Test
    public void testConvertByteToByte() {
        this.convertToSameTypeAndCheck(BYTE);
    }

    @Test
    public void testConvertDoubleToDouble() {
        this.convertToSameTypeAndCheck(DOUBLE);
    }

    @Test
    public void testConvertFloatToFloat() {
        this.convertToSameTypeAndCheck(FLOAT);
    }

    @Test
    public void testConvertIntegerToInteger() {
        this.convertToSameTypeAndCheck(INT);
    }

    @Test
    public void testConvertLongToLong() {
        this.convertToSameTypeAndCheck(LONG);
    }

    @Test
    public void testConvertShortToShort() {
        this.convertToSameTypeAndCheck(SHORT);
    }

    private void convertToSameTypeAndCheck(final Number number) {
        this.convertAndCheck(
            number,
            Cast.to(number.getClass()),
            number
        );
    }

    // toBigDecimal.....................................................................................................

    @Test
    public void testConvertBigIntegerToBigDecimal() {
        this.convertToBigDecimalAndCheck(BIG_INTEGER);
    }

    @Test
    public void testConvertByte123ToBigDecimal() {
        this.convertToBigDecimalAndCheck(
            (byte) 123,
            new BigDecimal(123)
        );
    }

    @Test
    public void testConvertByte255ToBigDecimal() {
        this.convertToBigDecimalAndCheck(
            (byte) 255,
            new BigDecimal(255)
        );
    }

    @Test
    public void testConvertDoubleToBigDecimal() {
        this.convertToBigDecimalAndCheck(DOUBLE);
    }

    @Test
    public void testConvertFloatToBigDecimal() {
        this.convertToBigDecimalAndCheck(FLOAT);
    }

    @Test
    public void testConvertIntegerToBigDecimal() {
        this.convertToBigDecimalAndCheck(INT);
    }

    @Test
    public void testConvertLongToBigDecimal() {
        this.convertToBigDecimalAndCheck(LONG);
    }

    @Test
    public void testConvertShortToBigDecimal() {
        this.convertToBigDecimalAndCheck(SHORT);
    }

    private void convertToBigDecimalAndCheck(final Number number) {
        this.convertToBigDecimalAndCheck(
            number,
            BIG_DECIMAL
        );
    }

    private void convertToBigDecimalAndCheck(final Number number,
                                             final BigDecimal expected) {
        this.convertAndCheck(
            number,
            BigDecimal.class,
            expected
        );
    }

    // toBigInteger.....................................................................................................

    @Test
    public void testConvertBigDecimalToBigInteger() {
        this.convertToBigIntegerAndCheck(BIG_DECIMAL);
    }

    @Test
    public void testConvertBigDecimalToBigIntegerFails() {
        this.convertToBigIntegerFails(BigDecimal.valueOf(1.5));
    }

    @Test
    public void testConvertByteToBigIntegerZero() {
        this.convertToBigIntegerAndCheck(
            (byte) 0,
            BigInteger.ZERO
        );
    }

    @Test
    public void testConvertByteToBigIntegerPositive() {
        this.convertToBigIntegerAndCheck(
            (byte) 123,
            BigInteger.valueOf(123)
        );
    }

    @Test
    public void testConvertByteToBigIntegerNegative() {
        this.convertToBigIntegerAndCheck(
            (byte) 255,
            BigInteger.valueOf(255)
        );
    }

    @Test
    public void testConvertDoubleToBigInteger() {
        this.convertToBigIntegerAndCheck(DOUBLE);
    }

    @Test
    public void testConvertDoubleToBigIntegerFails() {
        this.convertToBigIntegerFails(1.5);
    }

    @Test
    public void testConvertFloatToBigInteger() {
        this.convertToBigIntegerAndCheck(FLOAT);
    }

    @Test
    public void testConvertFloatToBigIntegerFails() {
        this.convertToBigIntegerFails(1.5f);
    }

    @Test
    public void testConvertIntegerToBigInteger() {
        this.convertToBigIntegerAndCheck(INT);
    }

    @Test
    public void testConvertLongToBigInteger() {
        this.convertToBigIntegerAndCheck(LONG);
    }

    @Test
    public void testConvertShortToBigInteger() {
        this.convertToBigIntegerAndCheck(SHORT);
    }

    private void convertToBigIntegerAndCheck(final Number number) {
        this.convertToBigIntegerAndCheck(
            number,
            BIG_INTEGER
        );
    }

    private void convertToBigIntegerAndCheck(final Number number,
                                             final BigInteger expected) {
        this.convertAndCheck(
            number,
            BigInteger.class,
            expected
        );
    }

    private void convertToBigIntegerFails(final Number number) {
        this.convertFails(
            number,
            BigInteger.class
        );
    }

    // toByte...........................................................................................................

    @Test
    public void testConvertBigDecimalToByte() {
        this.convertToByteAndCheck(BIG_DECIMAL);
    }

    @Test
    public void testConvertBigDecimalToByteFails() {
        this.convertToByteFails(BIG_DECIMAL_DOUBLE_MAX2);
    }

    @Test
    public void testConvertBigDecimalToByteFails2() {
        this.convertToByteFails(BigDecimal.valueOf(0.5));
    }

    @Test
    public void testConvertBigIntegerToByteWithMinusOneFails() {
        this.convertToByteFails(
            BigInteger.valueOf(-1)
        );
    }


    @Test
    public void testConvertBigIntegerToByteWithZero() {
        this.convertToByteAndCheck(
            BigInteger.ZERO,
            (byte) 0
        );
    }

    @Test
    public void testConvertBigIntegerToByteWith255() {
        this.convertToByteAndCheck(
            BigInteger.valueOf(255),
            (byte) 255
        );
    }

    @Test
    public void testConvertBigIntegerToByteWith256Fails() {
        this.convertToByteFails(
            BigInteger.valueOf(256)
        );
    }

    @Test
    public void testConvertBigIntegerToByteFails() {
        this.convertToByteFails(BIG_INTEGER_LONG_MAX2);
    }

    @Test
    public void testConvertDoubleToByte() {
        this.convertToByteAndCheck(DOUBLE);
    }

    @Test
    public void testConvertDoubleToByteWithPrecisionLoss() {
        this.convertToByteAndCheck(
            1.5,
            (byte) 1
        );
    }

    @Test
    public void testConvertDoubleToByteFails() {
        this.convertToByteFails(Double.MAX_VALUE);
    }

    @Test
    public void testConvertDoubleToByteWithMinusOneFails() {
        this.convertToByteFails(
            -1.0
        );
    }


    @Test
    public void testConvertDoubleToByteWithZero() {
        this.convertToByteAndCheck(
            0.0,
            (byte) 0
        );
    }

    @Test
    public void testConvertDoubleToByteWith255() {
        this.convertToByteAndCheck(
            255.0,
            (byte) 255
        );
    }

    @Test
    public void testConvertDoubleToByteWith256Fails() {
        this.convertToByteFails(
            256.0
        );
    }

    @Test
    public void testConvertFloatToByte() {
        this.convertToByteAndCheck(FLOAT);
    }

    @Test
    public void testConvertFloatToByteWithPrecisionLoss() {
        this.convertToByteAndCheck(
            1.5f,
            (byte) 1
        );
    }

    @Test
    public void testConvertFloatToByteFails() {
        this.convertToByteFails(Float.MAX_VALUE);
    }

    @Test
    public void testConvertFloatToByteWithMinusOneFails() {
        this.convertToByteFails(
            -1.0f
        );
    }


    @Test
    public void testConvertFloatToByteWithZero() {
        this.convertToByteAndCheck(
            0.0f,
            (byte) 0
        );
    }

    @Test
    public void testConvertFloatToByteWith255() {
        this.convertToByteAndCheck(
            255f,
            (byte) 255
        );
    }

    @Test
    public void testConvertFloatToByteWith256Fails() {
        this.convertToByteFails(
            256f
        );
    }

    @Test
    public void testConvertIntegerToByteWithMinusOneFails() {
        this.convertToByteFails(
            -1
        );
    }


    @Test
    public void testConvertIntegerToByteWithZero() {
        this.convertToByteAndCheck(
            0,
            (byte) 0
        );
    }

    @Test
    public void testConvertIntegerToByteWith255() {
        this.convertToByteAndCheck(
            255,
            (byte) 255
        );
    }

    @Test
    public void testConvertIntegerToByteWith256Fails() {
        this.convertToByteFails(
            256
        );
    }

    @Test
    public void testConvertLongToByte() {
        this.convertToByteAndCheck(LONG);
    }

    @Test
    public void testConvertLongToByteFails() {
        this.convertToByteFails(Long.MAX_VALUE);
    }

    @Test
    public void testConvertLongToByteWithMinusOneFails() {
        this.convertToByteFails(
            -1L
        );
    }


    @Test
    public void testConvertLongToByteWithZero() {
        this.convertToByteAndCheck(
            0L,
            (byte) 0
        );
    }

    @Test
    public void testConvertLongToByteWith255() {
        this.convertToByteAndCheck(
            255L,
            (byte) 255
        );
    }

    @Test
    public void testConvertLongToByteWith256Fails() {
        this.convertToByteFails(
            256L
        );
    }

    @Test
    public void testConvertShortToByteWithMinusOneFails() {
        this.convertToByteFails(
            (short)-1
        );
    }


    @Test
    public void testConvertShortToByteWithZero() {
        this.convertToByteAndCheck(
            (short)0,
            (byte) 0
        );
    }

    @Test
    public void testConvertShortToByteWith255() {
        this.convertToByteAndCheck(
            (short)255,
            (byte) 255
        );
    }

    @Test
    public void testConvertShortToByteWith256Fails() {
        this.convertToByteFails(
            (short)256
        );
    }

    private void convertToByteAndCheck(final Number number) {
        this.convertAndCheck(
            number,
            BYTE
        );
    }

    private void convertToByteAndCheck(final Number number,
                                       final Byte expected) {
        this.convertAndCheck(
            number,
            Byte.class,
            expected
        );
    }

    private void convertToByteFails(final Number number) {
        this.convertFails(number, Byte.class);
    }

    // toDouble........................................................................................................

    @Test
    public void testConvertBigDecimalToDouble() {
        this.convertToDoubleAndCheck(BIG_DECIMAL);
    }

    @Test
    public void testConvertBigIntegerToDouble() {
        this.convertToDoubleAndCheck(BIG_INTEGER);
    }

    @Test
    public void testConvertByteToDoubleWithZero() {
        this.convertToDoubleAndCheck(
            (byte) 0,
            0.0
        );
    }

    @Test
    public void testConvertByteToDoubleWithPositive() {
        this.convertToDoubleAndCheck(
            (byte) 123,
            123.0
        );
    }

    @Test
    public void testConvertByteToDoubleWithNegative() {
        this.convertToDoubleAndCheck(
            (byte) 255,
            255.0
        );
    }

    @Test
    public void testConvertFloatToDouble() {
        this.convertToDoubleAndCheck(FLOAT);
    }

    @Test
    public void testConvertLongToDouble() {
        this.convertToDoubleAndCheck(LONG);
    }

//    @Test // Long -> Double -> Long should be lossy but isnt at runtime.
//    public void testConvertLongToDoubleFails() {
//        this.convertToDoubleFails(Long.MAX_VALUE);
//    }

    @Test
    public void testConvertShortToDouble() {
        this.convertToDoubleAndCheck(SHORT);
    }

    private void convertToDoubleAndCheck(final Number number) {
        this.convertToDoubleAndCheck(
            number,
            DOUBLE
        );
    }

    private void convertToDoubleAndCheck(final Number number,
                                         final double expected) {
        this.convertAndCheck(
            number,
            Double.class,
            expected
        );
    }

    // toFloat..........................................................................................................

    @Test
    public void testConvertBigDecimalToFloat() {
        this.convertToFloatAndCheck(BIG_DECIMAL);
    }

    @Test
    public void testConvertBigDecimalToFloatFails() {
        this.convertToFloatFails(BIG_DECIMAL_DOUBLE_MAX2);
    }

    @Test
    public void testConvertBigIntegerToFloat() {
        this.convertToFloatAndCheck(BIG_INTEGER);
    }

    @Test
    public void testConvertBigIntegerToFloatWithPrecisionLoss() {
        this.convertToFloatAndCheck(
            BIG_INTEGER_LONG_MAX2,
            8.507059E37f
        );
    }

    @Test
    public void testConvertByteToFloatWithZero() {
        this.convertToFloatAndCheck(
            (byte) 0,
            0.0f
        );
    }

    @Test
    public void testConvertByteToFloatWithPositive() {
        this.convertToFloatAndCheck(
            (byte) 123,
            123.0f
        );
    }

    @Test
    public void testConvertByteToFloatWithNegative() {
        this.convertToFloatAndCheck(
            (byte) 255,
            255.0f
        );
    }

    @Test
    public void testConvertDoubleToFloat() {
        this.convertToFloatAndCheck(DOUBLE);
    }

    @Test
    public void testConvertDoubleToFloatFails() {
        this.convertToFloatFails(Double.MAX_VALUE);
    }

    @Test
    public void testConvertLongToFloat() {
        this.convertToFloatAndCheck(LONG);
    }

    @Test
    public void testConvertShortToFloat() {
        this.convertToFloatAndCheck(SHORT);
    }

    private void convertToFloatAndCheck(final Number number) {
        this.convertToFloatAndCheck(
            number,
            FLOAT
        );
    }

    private void convertToFloatAndCheck(final Number number,
                                        final Float expected) {
        this.convertAndCheck(
            number,
            Float.class,
            expected
        );
    }

    private void convertToFloatFails(final Number number) {
        this.convertFails(number, Float.class);
    }

    // toInteger........................................................................................................

    @Test
    public void testConvertBigDecimalToInteger() {
        this.convertToIntegerAndCheck(BIG_DECIMAL);
    }

    @Test
    public void testConvertBigDecimalToIntegerFails() {
        this.convertToIntegerFails(BIG_DECIMAL_DOUBLE_MAX2);
    }

    @Test
    public void testConvertBigDecimalToIntegerFails2() {
        this.convertToIntegerFails(BigDecimal.valueOf(0.5));
    }

    @Test
    public void testConvertBigIntegerToInteger() {
        this.convertToIntegerAndCheck(BIG_INTEGER);
    }

    @Test
    public void testConvertBigIntegerToIntegerFails() {
        this.convertToIntegerFails(BIG_INTEGER_LONG_MAX2);
    }

    @Test
    public void testConvertByteToIntegerWithZero() {
        this.convertToIntegerAndCheck(
            (byte) 0,
            0
        );
    }

    @Test
    public void testConvertByteToIntegerWithPositive() {
        this.convertToIntegerAndCheck(
            (byte) 123,
            123
        );
    }

    @Test
    public void testConvertByteToIntegerWithNegative() {
        this.convertToIntegerAndCheck(
            (byte) 255,
            255
        );
    }

    @Test
    public void testConvertDoubleToInteger() {
        this.convertToIntegerAndCheck(DOUBLE);
    }

    @Test
    public void testConvertDoubleToIntegerFails() {
        this.convertToIntegerFails(Double.MAX_VALUE);
    }

    @Test
    public void testConvertDoubleToIntegerWithPrecisionLoss() {
        this.convertToIntegerAndCheck(
            1.5,
            1
        );
    }

    @Test
    public void testConvertFloatToInteger() {
        this.convertToIntegerAndCheck(FLOAT);
    }

    @Test
    public void testConvertFloatToIntegerFails() {
        this.convertToIntegerFails(Float.MAX_VALUE);
    }

    @Test
    public void testConvertFloatToIntegerWithPrecisionLoss() {
        this.convertToIntegerAndCheck(
            2.5f,
            2
        );
    }

    @Test
    public void testConvertLongToInteger() {
        this.convertToIntegerAndCheck(LONG);
    }

    @Test
    public void testConvertLongToIntegerFails() {
        this.convertToIntegerFails(Long.MAX_VALUE);
    }

    @Test
    public void testConvertShortToInteger() {
        this.convertToIntegerAndCheck(SHORT);
    }

    private void convertToIntegerAndCheck(final Number number) {
        this.convertToIntegerAndCheck(
            number,
            INT
        );
    }

    private void convertToIntegerAndCheck(final Number number,
                                          final Integer expected) {
        this.convertAndCheck(
            number,
            Integer.class,
            expected
        );
    }

    private void convertToIntegerFails(final Number number) {
        this.convertFails(number, Integer.class);
    }

    // toLong...........................................................................................................

    @Test
    public void testConvertBigDecimalToLong() {
        this.convertToLongAndCheck(BIG_DECIMAL);
    }

    @Test
    public void testConvertBigDecimalToLongFails() {
        this.convertToLongFails(BIG_DECIMAL_DOUBLE_MAX2);
    }

    @Test
    public void testConvertBigDecimalToLongFails2() {
        this.convertToLongFails(BigDecimal.valueOf(0.5));
    }

    @Test
    public void testConvertBigIntegerToLong() {
        this.convertToLongAndCheck(BIG_INTEGER);
    }

    @Test
    public void testConvertBigIntegerToLongFails() {
        this.convertToLongFails(BIG_INTEGER_LONG_MAX2);
    }

    @Test
    public void testConvertByteToLongWithZero() {
        this.convertToLongAndCheck(
            (byte) 0,
            0L
        );
    }

    @Test
    public void testConvertByteToLongWithPositive() {
        this.convertToLongAndCheck(
            (byte) 123,
            123L
        );
    }

    @Test
    public void testConvertByteToLongWithNegative() {
        this.convertToLongAndCheck(
            (byte) 255,
            255L
        );
    }

    @Test
    public void testConvertDoubleToLong() {
        this.convertToLongAndCheck(DOUBLE);
    }

    @Test
    public void testConvertDoubleToLongFails() {
        this.convertToLongFails(Double.MAX_VALUE);
    }

    @Test
    public void testConvertDoubleToLongWithPrecisionLoss() {
        this.convertToLongAndCheck(
            1.5,
            1L
        );
    }

    @Test
    public void testConvertFloatToLong() {
        this.convertToLongAndCheck(FLOAT);
    }

    @Test
    public void testConvertFloatToLongFails() {
        this.convertToLongFails(Float.MAX_VALUE);
    }

    @Test
    public void testConvertFloatToLongWithPrecisionLoss() {
        this.convertToLongAndCheck(
            2.5f,
            2L
        );
    }

    @Test
    public void testConvertIntegerToLong() {
        this.convertToLongAndCheck(INT);
    }

    @Test
    public void testConvertShortToLong() {
        this.convertToLongAndCheck(SHORT);
    }

    private void convertToLongAndCheck(final Number number) {
        this.convertToLongAndCheck(
            number,
            LONG
        );
    }

    private void convertToLongAndCheck(final Number number,
                                       final long expected) {
        this.convertAndCheck(
            number,
            Long.class,
            expected
        );
    }

    private void convertToLongFails(final Number number) {
        this.convertFails(number, Long.class);
    }

    // toShort..........................................................................................................

    @Test
    public void testConvertBigDecimalToShort() {
        this.convertToShortAndCheck(BIG_DECIMAL);
    }

    @Test
    public void testConvertBigDecimalToShortFails() {
        this.convertToShortFails(BIG_DECIMAL_DOUBLE_MAX2);
    }

    @Test
    public void testConvertBigDecimalToShortFails2() {
        this.convertToShortFails(BigDecimal.valueOf(0.5));
    }

    @Test
    public void testConvertBigIntegerToShort() {
        this.convertToShortAndCheck(BIG_INTEGER);
    }

    @Test
    public void testConvertBigIntegerToShortFails() {
        this.convertToShortFails(BIG_INTEGER_LONG_MAX2);
    }

    @Test
    public void testConvertByteToShortWithZero() {
        this.convertToShortAndCheck(
            (byte) 0,
            (short) 0
        );
    }

    @Test
    public void testConvertByteToShortWithPositive() {
        this.convertToShortAndCheck(
            (byte) 123,
            (short) 123
        );
    }

    @Test
    public void testConvertByteToShortWithNegative() {
        this.convertToShortAndCheck(
            (byte) 255,
            (short) 255
        );
    }

    @Test
    public void testConvertDoubleToShort() {
        this.convertToShortAndCheck(DOUBLE);
    }

    @Test
    public void testConvertDoubleToShortFails() {
        this.convertToShortFails(Double.MAX_VALUE);
    }

    @Test
    public void testConvertDoubleToShortWithPrecisionLoss() {
        this.convertToShortAndCheck(
            1.5,
            (short) 1
        );
    }

    @Test
    public void testConvertFloatToShort() {
        this.convertToShortAndCheck(FLOAT);
    }

    @Test
    public void testConvertFloatToShortFails() {
        this.convertToShortFails(Float.MAX_VALUE);
    }

    @Test
    public void testConvertFloatToShortWithPrecisionLoss() {
        this.convertToShortAndCheck(
            2.5f,
            (short) 2
        );
    }

    @Test
    public void testConvertIntegerToShort() {
        this.convertToShortAndCheck(INT);
    }

    @Test
    public void testConvertIntegerToShortFails() {
        this.convertToShortFails(Integer.MAX_VALUE);
    }

    @Test
    public void testConvertLongToShort() {
        this.convertToShortAndCheck(LONG);
    }

    @Test
    public void testConvertLongToShortFails() {
        this.convertToShortFails(Long.MAX_VALUE);
    }

    private void convertToShortAndCheck(final Number number) {
        this.convertToShortAndCheck(
            number,
            SHORT
        );
    }

    private void convertToShortAndCheck(final Number number,
                                        final short expected) {
        this.convertAndCheck(
            number,
            Short.class,
            expected
        );
    }

    private void convertToShortFails(final Number number) {
        this.convertFails(number, Short.class);
    }

    // helper............................................................................................................

    @Override
    public ConverterNumberToNumber<ConverterContext> createConverter() {
        return ConverterNumberToNumber.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<ConverterNumberToNumber<ConverterContext>> type() {
        return Cast.to(ConverterNumberToNumber.class);
    }

    // TypeNameTesting..................................................................................................

    @Override
    public String typeNamePrefix() {
        return ConverterNumberToNumber.class.getSimpleName();
    }

    @Override
    public String typeNameSuffix() {
        return Number.class.getSimpleName() + "To" + Number.class.getSimpleName();
    }
}
