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
import walkingkooka.ToStringTesting;

import java.math.BigDecimal;

public final class ConverterObjectStringTest extends ConverterTestCase<ConverterObjectString<ConverterContext>>
        implements ConverterTesting2<ConverterObjectString<ConverterContext>, ConverterContext>,
        ToStringTesting<ConverterObjectString<ConverterContext>> {

    @Test
    public void testNull() {
        this.convertAndCheck(
                null,
                "null"
        );
    }

    @Test
    public void testBooleanTrue() {
        this.convertAndCheck(
                Boolean.TRUE,
                Boolean.TRUE.toString()
        );
    }

    @Test
    public void testLong() {
        this.convertAndCheck(
                123L,
                "123"
        );
    }

    @Test
    public void testDifferentTargetTypeFails() {
        this.convertFails(1L, BigDecimal.class);
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createConverter(), "*->String");
    }

    @Override
    public ConverterObjectString<ConverterContext> createConverter() {
        return ConverterObjectString.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<ConverterObjectString<ConverterContext>> type() {
        return Cast.to(ConverterObjectString.class);
    }

    // TypeNameTesting..................................................................................................

    @Override
    public String typeNamePrefix() {
        return Converter.class.getSimpleName();
    }

    @Override
    public String typeNameSuffix() {
        return Object.class.getSimpleName() + String.class.getSimpleName();
    }
}
