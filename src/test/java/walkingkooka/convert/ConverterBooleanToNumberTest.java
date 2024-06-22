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
    public void testNull() {
        this.convertAndCheck(
                null,
                Number.class,
                null
        );
    }

    @Test
    public void testBigDecimalTrue() {
        this.convertAndCheck(true, BigDecimal.ONE);
    }

    @Test
    public void testBigDecimalFalse() {
        this.convertAndCheck(false, BigDecimal.ZERO);
    }

    @Test
    public void testBigIntegerTrue() {
        this.convertAndCheck(true, BigInteger.ONE);
    }

    @Test
    public void testBigIntegerFalse() {
        this.convertAndCheck(false, BigInteger.ZERO);
    }

    @Test
    public void testByteTrue() {
        this.convertAndCheck(true, (byte) 1);
    }

    @Test
    public void testByteFalse() {
        this.convertAndCheck(false, (byte) 0);
    }

    @Test
    public void testDoubleTrue() {
        this.convertAndCheck(true, 1.0);
    }

    @Test
    public void testDoubleFalse() {
        this.convertAndCheck(false, 0.0);
    }

    @Test
    public void testFloatTrue() {
        this.convertAndCheck(true, 1.0f);
    }

    @Test
    public void testFloatFalse() {
        this.convertAndCheck(false, 0.0f);
    }

    @Test
    public void testIntegerTrue() {
        this.convertAndCheck(true, 1);
    }

    @Test
    public void testIntegerFalse() {
        this.convertAndCheck(false, 0);
    }

    @Test
    public void testLongTrue() {
        this.convertAndCheck(true, 1L);
    }

    @Test
    public void testLongFalse() {
        this.convertAndCheck(false, 0L);
    }

    @Test
    public void testShortTrue() {
        this.convertAndCheck(true, (short) 1);
    }

    @Test
    public void testShortFalse() {
        this.convertAndCheck(false, (short) 0);
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createConverter(), "Boolean->Number");
    }

    @Override
    public ConverterBooleanToNumber<ConverterContext> createConverter() {
        return ConverterBooleanToNumber.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<ConverterBooleanToNumber<ConverterContext>> type() {
        return Cast.to(ConverterBooleanToNumber.class);
    }

    // TypeNameTesting..................................................................................................

    @Override
    public String typeNamePrefix() {
        return Converter.class.getSimpleName();
    }

    @Override
    public String typeNameSuffix() {
        return Boolean.class.getSimpleName() + "To" + Number.class.getSimpleName();
    }
}
