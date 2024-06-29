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

public final class ConverterNumberToLocalTimeTest extends ConverterNumberTestCase<ConverterNumberToLocalTime<ConverterContext>, LocalTime> {

    private final static byte VALUE = 123;

    @Test
    public void testConvertNonNumberTypeFails() {
        this.convertFails2("fail!");
    }

    @Test
    public void testConvertFromLocalTimeFails() {
        this.convertFails2(LocalTime.of(12, 59));
    }

    @Test
    public void testConvertBigDecimal() {
        this.convertAndCheck2(BigDecimal.valueOf(VALUE));
    }

    @Test
    public void testConvertBigDecimalWithFraction() {
        this.convertAndCheck(
                BigDecimal.valueOf(123.5),
                LocalTime.ofSecondOfDay(VALUE)
                        .plusNanos(Converters.NANOS_PER_SECOND / 2)
        );
    }

    @Test
    public void testConvertBigInteger() {
        this.convertAndCheck2(BigInteger.valueOf(123));
    }

    @Test
    public void testConvertFloat() {
        this.convertAndCheck2((float) VALUE);
    }

    @Test
    public void testConvertDouble() {
        this.convertAndCheck2((double) VALUE);
    }

    @Test
    public void testConvertDoubleWithFraction() {
        this.convertAndCheck(BigDecimal.valueOf(123.5), LocalTime.ofSecondOfDay(VALUE).plusNanos(Converters.NANOS_PER_SECOND / 2));
    }

    @Override
    public void testConvertDoubleMaxFails() {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("RedundantCast")
    @Test
    public void testConvertByte() {
        this.convertAndCheck2((byte) VALUE);
    }

    @Test
    public void testConvertShort() {
        this.convertAndCheck2((short) VALUE);
    }

    @Test
    public void testConvertInteger() {
        this.convertAndCheck2((int) VALUE);
    }

    @Test
    public void testConvertLong() {
        this.convertAndCheck2((long) VALUE);
    }

    private void convertAndCheck2(final Object value) {
        this.convertAndCheck(value, LocalTime.ofSecondOfDay(VALUE));
    }

    @Override
    public ConverterNumberToLocalTime<ConverterContext> createConverter() {
        return ConverterNumberToLocalTime.instance();
    }

    @Override
    protected Class<LocalTime> targetType() {
        return LocalTime.class;
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    @Override
    public Class<ConverterNumberToLocalTime<ConverterContext>> type() {
        return Cast.to(ConverterNumberToLocalTime.class);
    }
}
