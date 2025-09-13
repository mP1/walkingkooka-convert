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

public final class ConverterToObjectTest extends ConverterTestCase2<ConverterToObject<ConverterContext>> {

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
            "* to Object"
        );
    }

    @Override
    public ConverterToObject<ConverterContext> createConverter() {
        return ConverterToObject.instance();
    }

    @Override
    public ConverterContext createContext() {
        return (ConverterContext) ConverterContexts.fake();
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<ConverterToObject<ConverterContext>> type() {
        return Cast.to(ConverterToObject.class);
    }
}
