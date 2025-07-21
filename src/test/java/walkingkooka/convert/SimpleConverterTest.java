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

import java.util.AbstractList;
import java.util.ArrayList;

public final class SimpleConverterTest implements ConverterTesting2<SimpleConverter<FakeConverterContext>, FakeConverterContext>,
    ToStringTesting<SimpleConverter<FakeConverterContext>> {

    @Test
    public void testConvertNull() {
        this.convertAndCheck(
            null,
            Void.class
        );
    }

    @Test
    public void testConvertSameType() {
        this.convertAndCheck(
            "ABC",
            String.class
        );
    }

    @Test
    public void testConvertSubClass() {
        this.convertAndCheck(
            new ArrayList(),
            AbstractList.class
        );
    }

    @Test
    public void testConvertSubClassObject() {
        this.convertAndCheck(
            this,
            Object.class
        );
    }

    @Test
    public void testConvertInstanceOfTargetType() {
        this.convertFails(
            "ABC",
            CharSequence.class
        );
    }

    @Test
    public void testConvertDifferentType() {
        this.convertFails(
            "ABC",
            Number.class
        );
    }

    @Override
    public SimpleConverter<FakeConverterContext> createConverter() {
        return SimpleConverter.instance();
    }

    @Override
    public FakeConverterContext createContext() {
        return (FakeConverterContext) ConverterContexts.fake();
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "if type"
        );
    }

    // Class............................................................................................................

    @Override
    public Class<SimpleConverter<FakeConverterContext>> type() {
        return Cast.to(SimpleConverter.class);
    }
}
