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

public final class ConverterObjectTest extends ConverterTestCase<ConverterObject<ConverterContext>>
        implements ConverterTesting2<ConverterObject<ConverterContext>, ConverterContext>,
        ToStringTesting<ConverterObject<ConverterContext>> {

    @Test
    public void testConvertNullToObject() {
        this.convertAndCheck(
                null,
                Object.class
        );
    }

    @Test
    public void testConvertNonNullObject() {
        this.convertAndCheck(
                Boolean.TRUE,
                Object.class
        );
    }

    @Test
    public void testConvertNullToNonObject() {
        this.convertFails(
                null,
                Void.class
        );
    }

    @Test
    public void testConvertNonNullToNonObject() {
        this.convertFails(
                "1",
                Void.class
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createConverter(),
                "*->Object"
        );
    }

    @Override
    public ConverterObject<ConverterContext> createConverter() {
        return ConverterObject.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<ConverterObject<ConverterContext>> type() {
        return Cast.to(ConverterObject.class);
    }

    // TypeNameTesting..................................................................................................

    @Override
    public String typeNamePrefix() {
        return Converter.class.getSimpleName();
    }

    @Override
    public String typeNameSuffix() {
        return Object.class.getSimpleName();
    }
}
