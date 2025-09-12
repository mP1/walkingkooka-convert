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

public final class ConverterBooleanToNumberTest extends ConverterTestCase2<ConverterBooleanToNumber<ConverterContext>> {

    @Test
    public void testConvertNull() {
        this.convertAndCheck(
            null,
            Number.class
        );
    }

    @Test
    public void testConvertTrueToBigDecimal() {
        this.convertAndCheck(
            true,
            BigDecimal.ONE
        );
    }

    @Test
    public void testConvertFalseToBigDecimal() {
        this.convertAndCheck(
            false,
            BigDecimal.ZERO
        );
    }

    @Test
    public void testConvertTrueToBigInteger() {
        this.convertAndCheck(
            true,
            BigInteger.ONE
        );
    }

    @Test
    public void testConvertFalseToBigInteger() {
        this.convertAndCheck(
            false,
            BigInteger.ZERO
        );
    }

    @Test
    public void testConvertTrueToByte() {
        this.convertAndCheck(
            true,
            (byte) 1
        );
    }

    @Test
    public void testConvertFalseToByte() {
        this.convertAndCheck(
            false,
            (byte) 0
        );
    }

    @Test
    public void testConvertTrueToDouble() {
        this.convertAndCheck(
            true,
            1.0
        );
    }

    @Test
    public void testConvertFalseToDouble() {
        this.convertAndCheck(
            false,
            0.0
        );
    }

    @Test
    public void testConvertTrueFloat() {
        this.convertAndCheck(
            true,
            1.0f
        );
    }

    @Test
    public void testConvertFalseToFloat() {
        this.convertAndCheck(
            false,
            0.0f
        );
    }

    @Test
    public void testConvertTrueToInteger() {
        this.convertAndCheck(
            true,
            1
        );
    }

    @Test
    public void testConvertFalseToInteger() {
        this.convertAndCheck(
            false,
            0
        );
    }

    @Test
    public void testConvertTrueToLong() {
        this.convertAndCheck(
            true,
            1L
        );
    }

    @Test
    public void testConvertFalseToLong() {
        this.convertAndCheck(
            false,
            0L
        );
    }

    @Test
    public void testConvertTrueToShort() {
        this.convertAndCheck(
            true,
            (short) 1
        );
    }

    @Test
    public void testConvertFalseToShort() {
        this.convertAndCheck(
            false,
            (short) 0
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "Boolean to Number"
        );
    }

    @Override
    public ConverterBooleanToNumber<ConverterContext> createConverter() {
        return ConverterBooleanToNumber.instance();
    }

    @Override
    public FakeConverterContext createContext() {
        return (FakeConverterContext) ConverterContexts.fake();
    }

    // class............................................................................................................

    @Override
    public Class<ConverterBooleanToNumber<ConverterContext>> type() {
        return Cast.to(ConverterBooleanToNumber.class);
    }

    @Override
    public String typeNamePrefix() {
        return Converter.class.getSimpleName() + Boolean.class.getSimpleName() + "To" + Number.class.getSimpleName();
    }

    @Override
    public String typeNameSuffix() {
        return "";
    }
}
