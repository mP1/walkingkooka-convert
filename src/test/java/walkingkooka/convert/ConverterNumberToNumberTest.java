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

    // to Number........................................................................................................

    @Test
    public void testConvertBigDecimalToNumber() {
        this.convertToNumberAndCheck(this.bigDecimal());
    }

    @Test
    public void testConvertBigIntegerToNumber() {
        this.convertToNumberAndCheck(this.bigInteger());
    }

    @Test
    public void testConvertByteToNumber() {
        this.convertToNumberAndCheck(this.byteValue());
    }

    @Test
    public void testConvertDoubleToNumber() {
        this.convertToNumberAndCheck(this.doubleValue());
    }

    @Test
    public void testConvertFloatToNumber() {
        this.convertToNumberAndCheck(this.floatValue());
    }

    @Test
    public void testConvertIntegerToNumber() {
        this.convertToNumberAndCheck(this.integerValue());
    }

    @Test
    public void testConvertLongToNumber() {
        this.convertToNumberAndCheck(this.longValue());
    }

    @Test
    public void testConvertShortToNumber() {
        this.convertToNumberAndCheck(this.shortValue());
    }

    private void convertToNumberAndCheck(final Number number) {
        this.convertAndCheck(number, Number.class, number);
    }

    // to same type.....................................................................................................

    @Test
    public void testConvertBigDecimalToBigDecimal() {
        this.convertToSameTypeAndCheck(this.bigDecimal());
    }

    @Test
    public void testConvertBigIntegerToBigInteger() {
        this.convertToSameTypeAndCheck(this.bigInteger());
    }

    @Test
    public void testConvertByteToByte() {
        this.convertToSameTypeAndCheck(this.byteValue());
    }

    @Test
    public void testConvertDoubleToDouble() {
        this.convertToSameTypeAndCheck(this.doubleValue());
    }

    @Test
    public void testConvertFloatToFloat() {
        this.convertToSameTypeAndCheck(this.floatValue());
    }

    @Test
    public void testConvertIntegerToInteger() {
        this.convertToSameTypeAndCheck(this.integerValue());
    }

    @Test
    public void testConvertLongToLong() {
        this.convertToSameTypeAndCheck(this.longValue());
    }

    @Test
    public void testConvertShortToShort() {
        this.convertToSameTypeAndCheck(this.shortValue());
    }

    private void convertToSameTypeAndCheck(final Number number) {
        this.convertAndCheck(number, Number.class, number);
    }

    // toBigDecimal.....................................................................................................

    @Test
    public void testConvertBigIntegerToBigDecimal() {
        this.convertToBigDecimalAndCheck(this.bigInteger());
    }

    @Test
    public void testConvertByteToBigDecimal() {
        this.convertToBigDecimalAndCheck(this.byteValue());
    }
    
    @Test
    public void testConvertDoubleToBigDecimal() {
        this.convertToBigDecimalAndCheck(this.doubleValue());
    }

    @Test
    public void testConvertFloatToBigDecimal() {
        this.convertToBigDecimalAndCheck(this.floatValue());
    }
    
    @Test
    public void testConvertIntegerToBigDecimal() {
        this.convertToBigDecimalAndCheck(this.integerValue());
    }
    
    @Test
    public void testConvertLongToBigDecimal() {
        this.convertToBigDecimalAndCheck(this.longValue());
    }

    @Test
    public void testConvertShortToBigDecimal() {
        this.convertToBigDecimalAndCheck(this.shortValue());
    }
    
    private void convertToBigDecimalAndCheck(final Number number) {
        this.convertAndCheck(number, BigDecimal.class, this.bigDecimal());
    }

    // toBigInteger.....................................................................................................

    @Test
    public void testConvertBigDecimalToBigInteger() {
        this.convertToBigIntegerAndCheck(this.bigDecimal());
    }

    @Test
    public void testConvertBigDecimalToBigIntegerFails() {
        this.convertToBigIntegerFails(BigDecimal.valueOf(1.5));
    }

    @Test
    public void testConvertByteToBigInteger() {
        this.convertToBigIntegerAndCheck(this.byteValue());
    }

    @Test
    public void testConvertDoubleToBigInteger() {
        this.convertToBigIntegerAndCheck(this.doubleValue());
    }

    @Test
    public void testConvertDoubleToBigIntegerFails() {
        this.convertToBigIntegerFails(1.5);
    }

    @Test
    public void testConvertFloatToBigInteger() {
        this.convertToBigIntegerAndCheck(this.floatValue());
    }

    @Test
    public void testConvertFloatToBigIntegerFails() {
        this.convertToBigIntegerFails(1.5f);
    }

    @Test
    public void testConvertIntegerToBigInteger() {
        this.convertToBigIntegerAndCheck(this.integerValue());
    }

    @Test
    public void testConvertLongToBigInteger() {
        this.convertToBigIntegerAndCheck(this.longValue());
    }

    @Test
    public void testConvertShortToBigInteger() {
        this.convertToBigIntegerAndCheck(this.shortValue());
    }

    private void convertToBigIntegerAndCheck(final Number number) {
        this.convertAndCheck(number, BigInteger.class, this.bigInteger());
    }

    private void convertToBigIntegerFails(final Number number) {
        this.convertFails(number, BigInteger.class);
    }
    
    // toByte...........................................................................................................

    @Test
    public void testConvertBigDecimalToByte() {
        this.convertToByteAndCheck(this.bigDecimal());
    }

    @Test
    public void testConvertBigDecimalToByteFails() {
        this.convertToByteFails(this.bigDecimalDoubleMax2());
    }

    @Test
    public void testConvertBigDecimalToByteFails2() {
        this.convertToByteFails(BigDecimal.valueOf(0.5));
    }

    @Test
    public void testConvertBigIntegerToByte() {
        this.convertToByteAndCheck(this.bigInteger());
    }

    @Test
    public void testConvertBigIntegerToByteFails() {
        this.convertToByteFails(this.bigIntegerLongMax2());
    }

    @Test
    public void testConvertDoubleToByte() {
        this.convertToByteAndCheck(this.doubleValue());
    }

    @Test
    public void testConvertDoubleToByteWithPrecisionLoss() {
        this.convertToByteAndCheck(
                1.5,
                (byte)1
        );
    }

    @Test
    public void testConvertDoubleToByteFails() {
        this.convertToByteFails(Double.MAX_VALUE);
    }

    @Test
    public void testConvertFloatToByte() {
        this.convertToByteAndCheck(this.floatValue());
    }

    @Test
    public void testConvertFloatToByteWithPrecisionLoss() {
        this.convertToByteAndCheck(
                1.5f,
                (byte)1
        );
    }

    @Test
    public void testConvertFloatToByteFails() {
        this.convertToByteFails(Float.MAX_VALUE);
    }

    @Test
    public void testConvertIntegerToByte() {
        this.convertToByteAndCheck(this.integerValue());
    }

    @Test
    public void testConvertIntegerToByteFails() {
        this.convertToByteFails(Integer.MAX_VALUE);
    }

    @Test
    public void testConvertLongToByte() {
        this.convertToByteAndCheck(this.longValue());
    }

    @Test
    public void testConvertLongToByteFails() {
        this.convertToByteFails(Long.MAX_VALUE);
    }

    @Test
    public void testConvertShortToByte() {
        this.convertToByteAndCheck(this.shortValue());
    }

    @Test
    public void testConvertShortToByteFails() {
        this.convertToByteFails(Short.MAX_VALUE);
    }

    private void convertToByteAndCheck(final Number number) {
        this.convertAndCheck(
                number,
                this.byteValue()
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
        this.convertToDoubleAndCheck(this.bigDecimal());
    }

    @Test
    public void testConvertBigIntegerToDouble() {
        this.convertToDoubleAndCheck(this.bigInteger());
    }

    @Test
    public void testConvertByteToDouble() {
        this.convertToDoubleAndCheck(this.byteValue());
    }

    @Test
    public void testConvertFloatToDouble() {
        this.convertToDoubleAndCheck(this.floatValue());
    }

    @Test
    public void testConvertLongToDouble() {
        this.convertToDoubleAndCheck(this.longValue());
    }

//    @Test // Long -> Double -> Long should be lossy but isnt at runtime.
//    public void testConvertLongToDoubleFails() {
//        this.convertToDoubleFails(Long.MAX_VALUE);
//    }

    @Test
    public void testConvertShortToDouble() {
        this.convertToDoubleAndCheck(this.shortValue());
    }

    private void convertToDoubleAndCheck(final Number number) {
        this.convertAndCheck(number, Double.class, this.doubleValue());
    }

    // toFloat..........................................................................................................

    @Test
    public void testConvertBigDecimalToFloat() {
        this.convertToFloatAndCheck(this.bigDecimal());
    }

    @Test
    public void testConvertBigDecimalToFloatFails() {
        this.convertToFloatFails(this.bigDecimalDoubleMax2());
    }

    @Test
    public void testConvertBigIntegerToFloat() {
        this.convertToFloatAndCheck(this.bigInteger());
    }

    @Test
    public void testConvertBigIntegerToFloatWithPrecisionLoss() {
        this.convertToFloatAndCheck(
                this.bigIntegerLongMax2(),
                8.507059E37f
        );
    }

    @Test
    public void testConvertByteToFloat() {
        this.convertToFloatAndCheck(this.byteValue());
    }

    @Test
    public void testConvertDoubleToFloat() {
        this.convertToFloatAndCheck(this.doubleValue());
    }

    @Test
    public void testConvertDoubleToFloatFails() {
        this.convertToFloatFails(Double.MAX_VALUE);
    }

    @Test
    public void testConvertLongToFloat() {
        this.convertToFloatAndCheck(this.longValue());
    }

    @Test
    public void testConvertShortToFloat() {
        this.convertToFloatAndCheck(this.shortValue());
    }

    private void convertToFloatAndCheck(final Number number) {
        this.convertToFloatAndCheck(
                number,
                this.floatValue()
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
        this.convertToIntegerAndCheck(this.bigDecimal());
    }

    @Test
    public void testConvertBigDecimalToIntegerFails() {
        this.convertToIntegerFails(this.bigDecimalDoubleMax2());
    }

    @Test
    public void testConvertBigDecimalToIntegerFails2() {
        this.convertToIntegerFails(BigDecimal.valueOf(0.5));
    }

    @Test
    public void testConvertBigIntegerToInteger() {
        this.convertToIntegerAndCheck(this.bigInteger());
    }

    @Test
    public void testConvertBigIntegerToIntegerFails() {
        this.convertToIntegerFails(this.bigIntegerLongMax2());
    }

    @Test
    public void testConvertByteToInteger() {
        this.convertToIntegerAndCheck(this.byteValue());
    }

    @Test
    public void testConvertDoubleToInteger() {
        this.convertToIntegerAndCheck(this.doubleValue());
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
        this.convertToIntegerAndCheck(this.floatValue());
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
        this.convertToIntegerAndCheck(this.longValue());
    }

    @Test
    public void testConvertLongToIntegerFails() {
        this.convertToIntegerFails(Long.MAX_VALUE);
    }

    @Test
    public void testConvertShortToInteger() {
        this.convertToIntegerAndCheck(this.shortValue());
    }

    private void convertToIntegerAndCheck(final Number number) {
        this.convertToIntegerAndCheck(
                number,
                this.integerValue()
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
        this.convertToLongAndCheck(this.bigDecimal());
    }

    @Test
    public void testConvertBigDecimalToLongFails() {
        this.convertToLongFails(this.bigDecimalDoubleMax2());
    }

    @Test
    public void testConvertBigDecimalToLongFails2() {
        this.convertToLongFails(BigDecimal.valueOf(0.5));
    }

    @Test
    public void testConvertBigIntegerToLong() {
        this.convertToLongAndCheck(this.bigInteger());
    }

    @Test
    public void testConvertBigIntegerToLongFails() {
        this.convertToLongFails(this.bigIntegerLongMax2());
    }

    @Test
    public void testConvertByteToLong() {
        this.convertToLongAndCheck(this.byteValue());
    }

    @Test
    public void testConvertDoubleToLong() {
        this.convertToLongAndCheck(this.doubleValue());
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
        this.convertToLongAndCheck(this.floatValue());
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
        this.convertToLongAndCheck(this.integerValue());
    }

    @Test
    public void testConvertShortToLong() {
        this.convertToLongAndCheck(this.shortValue());
    }

    private void convertToLongAndCheck(final Number number) {
        this.convertToLongAndCheck(
                number,
                this.longValue()
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
        this.convertToShortAndCheck(this.bigDecimal());
    }

    @Test
    public void testConvertBigDecimalToShortFails() {
        this.convertToShortFails(this.bigDecimalDoubleMax2());
    }

    @Test
    public void testConvertBigDecimalToShortFails2() {
        this.convertToShortFails(BigDecimal.valueOf(0.5));
    }

    @Test
    public void testConvertBigIntegerToShort() {
        this.convertToShortAndCheck(this.bigInteger());
    }

    @Test
    public void testConvertBigIntegerToShortFails() {
        this.convertToShortFails(this.bigIntegerLongMax2());
    }

    @Test
    public void testConvertByteToShort() {
        this.convertToShortAndCheck(this.byteValue());
    }

    @Test
    public void testConvertDoubleToShort() {
        this.convertToShortAndCheck(this.doubleValue());
    }

    @Test
    public void testConvertDoubleToShortFails() {
        this.convertToShortFails(Double.MAX_VALUE);
    }

    @Test
    public void testConvertDoubleToShortWithPrecisionLoss() {
        this.convertToShortAndCheck(
                1.5,
                (short)1
        );
    }

    @Test
    public void testConvertFloatToShort() {
        this.convertToShortAndCheck(this.floatValue());
    }

    @Test
    public void testConvertFloatToShortFails() {
        this.convertToShortFails(Float.MAX_VALUE);
    }

    @Test
    public void testConvertFloatToShortWithPrecisionLoss() {
        this.convertToShortAndCheck(
                2.5f,
                (short)2
        );
    }

    @Test
    public void testConvertIntegerToShort() {
        this.convertToShortAndCheck(this.integerValue());
    }

    @Test
    public void testConvertIntegerToShortFails() {
        this.convertToShortFails(Integer.MAX_VALUE);
    }

    @Test
    public void testConvertLongToShort() {
        this.convertToShortAndCheck(this.longValue());
    }

    @Test
    public void testConvertLongToShortFails() {
        this.convertToShortFails(Long.MAX_VALUE);
    }

    private void convertToShortAndCheck(final Number number) {
        this.convertToShortAndCheck(
                number,
                this.shortValue()
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

    private final static Byte VALUE = 123;

    private BigDecimal bigDecimal() {
        return BigDecimal.valueOf(VALUE);
    }

    private BigDecimal bigDecimalDoubleMax2() {
        return BigDecimal.valueOf(Double.MAX_VALUE).multiply(BigDecimal.valueOf(Double.MAX_VALUE));
    }

    private BigInteger bigInteger() {
        return BigInteger.valueOf(VALUE);
    }

    private BigInteger bigIntegerLongMax2() {
        return BigInteger.valueOf(Long.MAX_VALUE).multiply(BigInteger.valueOf(Long.MAX_VALUE));
    }

    @SuppressWarnings("UnnecessaryUnboxing")
    private Byte byteValue() {
        return VALUE.byteValue();
    }

    private Double doubleValue() {
        return VALUE.doubleValue();
    }

    private Float floatValue() {
        return VALUE.floatValue();
    }

    private Integer integerValue() {
        return VALUE.intValue();
    }

    private Long longValue() {
        return VALUE.longValue();
    }

    private Short shortValue() {
        return VALUE.shortValue();
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
