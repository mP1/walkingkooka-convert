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
    public void testConvertStringToLocalTimeFails() {
        this.convertFails2("fail!");
    }

    @Test
    public void testConvertLocalTimeToLocalTimeFails() {
        this.convertFails2(
            LocalTime.of(12, 59)
        );
    }

    @Test
    public void testConvertBigDecimalToLocalTime() {
        this.convertAndCheck2(BigDecimal.valueOf(VALUE));
    }

    @Test
    public void testConvertBigDecimalWithFractionToLocalTime() {
        this.convertAndCheck(
            BigDecimal.valueOf(123.5),
            LocalTime.ofSecondOfDay(VALUE)
                .plusNanos(Converters.NANOS_PER_SECOND / 2)
        );
    }

    @Test
    public void testConvertBigIntegerToLocalTime() {
        this.convertAndCheck2(BigInteger.valueOf(123));
    }

    @Test
    public void testConvertFloatToLocalTime() {
        this.convertAndCheck2((float) VALUE);
    }

    @Test
    public void testConvertDoubleToLocalTime() {
        this.convertAndCheck2((double) VALUE);
    }

    @Test
    public void testConvertDoubleWithFractionToLocalTime() {
        this.convertAndCheck(
            BigDecimal.valueOf(123.5),
            LocalTime.ofSecondOfDay(VALUE)
                .plusNanos(Converters.NANOS_PER_SECOND / 2)
        );
    }

    @Override
    public void testConvertDoubleMaxFails() {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("RedundantCast")
    @Test
    public void testConvertByteToLocalTime() {
        this.convertAndCheck2((byte) VALUE);
    }

    @Test
    public void testConvertShortToLocalTime() {
        this.convertAndCheck2((short) VALUE);
    }

    @Test
    public void testConvertIntegerToLocalTime() {
        this.convertAndCheck2((int) VALUE);
    }

    @Test
    public void testConvertLongToLocalTime() {
        this.convertAndCheck2((long) VALUE);
    }

    private void convertAndCheck2(final Object value) {
        this.convertAndCheck(
            value,
            LocalTime.ofSecondOfDay(VALUE)
        );
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

    // class............................................................................................................

    @Override
    public Class<ConverterNumberToLocalTime<ConverterContext>> type() {
        return Cast.to(ConverterNumberToLocalTime.class);
    }
}
