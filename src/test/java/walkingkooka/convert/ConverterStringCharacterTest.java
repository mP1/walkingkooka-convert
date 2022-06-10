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

public final class ConverterStringCharacterTest extends ConverterTestCase2<ConverterStringCharacter<ConverterContext>> {

    @Test
    public void testNull() {
        this.convertAndCheck(
                null,
                Character.class,
                null
        );
    }

    @Test
    public void testEmptyFails() {
        this.convertFails("", Character.class);
    }

    @Test
    public void testLength2Fails() {
        this.convertFails("ab", Character.class);
    }

    @Test
    public void testOne() {
        this.convertAndCheck(
                "!",
                '!'
        );
    }

    @Test
    public void testOne2() {
        this.convertAndCheck(
                "A",
                'A'
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createConverter(), "String->Character");
    }

    @Override
    public ConverterStringCharacter<ConverterContext> createConverter() {
        return ConverterStringCharacter.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<ConverterStringCharacter<ConverterContext>> type() {
        return Cast.to(ConverterStringCharacter.class);
    }

    // TypeNameTesting..................................................................................................

    @Override
    public String typeNamePrefix() {
        return Converter.class.getSimpleName();
    }

    @Override
    public String typeNameSuffix() {
        return String.class.getSimpleName() + Character.class.getSimpleName();
    }
}
