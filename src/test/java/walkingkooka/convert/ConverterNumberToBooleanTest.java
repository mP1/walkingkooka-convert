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

public final class ConverterNumberToBooleanTest extends ConverterNumberTestCase<ConverterNumberToBoolean<ConverterContext>, Boolean> {

    // BigDecimal..................................................

    @Test
    public void testConvertBigDecimalNumberNonZero() {
        this.convertAndCheckTrue(BigDecimal.valueOf(+1));
    }

    @Test
    public void testConvertBigDecimalNumberNonZero2() {
        this.convertAndCheckTrue(BigDecimal.valueOf(-1));
    }

    @Test
    public void testConvertBigDecimalNumberZero() {
        this.convertAndCheckFalse(BigDecimal.valueOf(0));
    }

    // BigInteger..............................................................................

    @Test
    public void testConvertBigIntegerNumberNonZero() {
        this.convertAndCheckTrue(BigInteger.valueOf(+1));
    }

    @Test
    public void testConvertBigIntegerNumberNonZero2() {
        this.convertAndCheckTrue(BigInteger.valueOf(-1));
    }

    @Test
    public void testConvertBigIntegerNumberZero() {
        this.convertAndCheckFalse(BigInteger.valueOf(0));
    }

    // Double..............................................................................

    @Override
    public void testConvertDoubleNanFails() {
    }

    @Override
    public void testConvertDoublePositiveInfinityFails() {
    }

    @Test
    public void testConvertDoubleNegativeInfinityFails() {
    }

    @Override
    public void testConvertDoubleMaxFails() {
    }

    @Override
    public void testConvertDoubleMinFails() {
    }

    @Test
    public void testConvertDoubleNan() {
        this.convertAndCheckFalse(Double.NaN);
    }

    @Test
    public void testConvertDoublePositiveInfinity() {
        this.convertAndCheckFalse(Double.POSITIVE_INFINITY);
    }

    @Test
    public void testConvertDoubleNegativeInfinity() {
        this.convertAndCheckFalse(Double.NEGATIVE_INFINITY);
    }

    @Test
    public void testConvertDoubleMax() {
        this.convertAndCheckTrue(Double.MAX_VALUE);
    }

    @Test
    public void testConvertDoubleMin() {
        this.convertAndCheckTrue(Double.MIN_VALUE);
    }

    @Test
    public void testConvertDoubleNumberNonZero() {
        this.convertAndCheckTrue(-1.0);
    }

    @Test
    public void testConvertDoubleNumberNonZero2() {
        this.convertAndCheckTrue(1.0);
    }

    @Test
    public void testConvertDoubleNumberZero() {
        this.convertAndCheckFalse(0.0);
    }

    // Long..............................................................................

    @Test
    public void testConvertLongNumberNonZero() {
        this.convertAndCheckTrue(-1L);
    }

    @Test
    public void testConvertLongNumberNonZero2() {
        this.convertAndCheckTrue(1L);
    }

    @Test
    public void testConvertLongNumberZero() {
        this.convertAndCheckFalse(0L);
    }

    // String......

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createConverter(),
                "Number to Boolean"
        );
    }

    // helper............................................................................................................

    private void convertAndCheckTrue(final Number number) {
        this.convertAndCheck(
                number,
                true
        );
    }

    private void convertAndCheckFalse(final Number number) {
        this.convertAndCheck(
                number,
                false
        );
    }

    @Override
    public ConverterNumberToBoolean<ConverterContext> createConverter() {
        return ConverterNumberToBoolean.instance();
    }

    @Override
    protected Class<Boolean> targetType() {
        return Boolean.class;
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    @Override
    public Class<ConverterNumberToBoolean<ConverterContext>> type() {
        return Cast.to(ConverterNumberToBoolean.class);
    }
}
