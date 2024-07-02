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

    private final static byte VALUE = 123;
    private final static double WITH_NANOS = 123.5;

    @Test
    public void testConvertNull() {
        this.convertAndCheck(
                null,
                Number.class
        );
    }

    @Test
    public void testConvertLocalTimeToBigDecimal() {
        this.convertAndCheck2(BigDecimal.valueOf(VALUE));
    }

    @Test
    public void testConvertLocalTimeToBigDecimal2() {
        this.convertAndCheck3(BigDecimal.valueOf(WITH_NANOS));
    }

    @Test
    public void testConvertLocalTimeToBigInteger() {
        this.convertAndCheck2(BigInteger.valueOf(VALUE));
    }

    @Test
    public void testConvertLocalTimeWithNanosToBigIntegerFails() {
        this.convertFails2(BigInteger.class);
    }

    @Test
    public void testConvertLocalTimeToByte() {
        this.convertAndCheck2(VALUE);
    }

    @Test
    public void testConvertLocalTimeWithNanosToByteFails() {
        this.convertFails2(Byte.class);
    }

    @Test
    public void testConvertLocalTimeToShort() {
        this.convertAndCheck2((short) VALUE);
    }

    @Test
    public void testConvertLocalTimeWithNanosToShortFails() {
        this.convertFails2(Short.class);
    }

    @Test
    public void testConvertLocalTimeToInteger() {
        this.convertAndCheck2((int) VALUE);
    }

    @Test
    public void testConvertLocalTimeWithNanosToIntegerFails() {
        this.convertFails2(Integer.class);
    }

    @Test
    public void testConvertLocalTimeToLong() {
        this.convertAndCheck2((long) VALUE);
    }

    @Test
    public void testConvertLocalTimeWithNanosToLongFails() {
        this.convertFails2(Long.class);
    }

    @Test
    public void testConvertLocalTimeToFloat() {
        this.convertAndCheck2((float) VALUE);
    }

    @Test
    public void testConvertLocalTimeToDouble() {
        this.convertAndCheck2((double) VALUE);
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createConverter(),
                "LocalTime to Number"
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

    private void convertAndCheck2(final Number expected) {
        this.convertAndCheck(
                LocalTime.ofSecondOfDay(VALUE),
                expected
        );
    }

    private void convertAndCheck3(final Number expected) {
        this.convertAndCheck(this.withNanos(),
                Cast.to(expected.getClass()),
                expected);
    }

    private void convertFails2(final Class<?> target) {
        this.convertFails(this.withNanos(), target);
    }

    private LocalTime withNanos() {
        return LocalTime.ofSecondOfDay(VALUE).plusNanos(500000000);
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
