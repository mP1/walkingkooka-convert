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

    private final static double VALUE = 0.5;

    private final static LocalTime VALUE_TIME = LocalTime.NOON;

    private final static double VALUE2 = 0.25f;

    private final static LocalTime VALUE2_TIME = LocalTime.of(6, 0, 0);

    private final static double VALUE_ZERO = 0f;

    private final static LocalTime VALUE_ZERO_TIME = LocalTime.MIDNIGHT;

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
        this.convertAndCheck2(
            BigDecimal.valueOf(VALUE)
        );
    }

    @Test
    public void testConvertBigDecimalToLocalTime2() {
        this.convertAndCheck(
            BigDecimal.valueOf(VALUE2),
            VALUE2_TIME
        );
    }

    @Test
    public void testConvertBigIntegerZeroToLocalTime() {
        this.convertAndCheck(
            BigInteger.ZERO,
            LocalTime.MIDNIGHT
        );
    }

    @Test
    public void testConvertBigIntegerOneToLocalTime() {
        this.convertFails(
            BigInteger.ONE,
            LocalTime.class
        );
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
    public void testConvertDoubleToLocalTime2() {
        this.convertAndCheck(
            VALUE2,
            VALUE2_TIME
        );
    }

    @Test
    public void testConvertDoubleToLocalTime3() {
        this.convertAndCheck(
            0.0,
            LocalTime.MIDNIGHT
        );
    }

    @Override
    public void testConvertDoubleMaxFails() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void testConvertDoubleMinFails() {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("RedundantCast")
    @Test
    public void testConvertByteToLocalTime() {
        this.convertAndCheck(
            (byte) VALUE_ZERO,
            VALUE_ZERO_TIME
        );
    }

    @Test
    public void testConvertShortToLocalTime() {
        this.convertAndCheck(
            (short) VALUE_ZERO,
            VALUE_ZERO_TIME
        );
    }

    @Test
    public void testConvertIntegerToLocalTime() {
        this.convertAndCheck(
            (int) VALUE_ZERO,
            VALUE_ZERO_TIME
        );
    }

    @Test
    public void testConvertLongToLocalTime() {
        this.convertAndCheck(
            (long) VALUE_ZERO,
            VALUE_ZERO_TIME
        );
    }

    private void convertAndCheck2(final Object value) {
        this.convertAndCheck(
            value,
            VALUE_TIME
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
