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

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ConverterConverterStringCharacterTest implements ConverterTesting2<ConverterConverterStringCharacter<FakeConverterContext>, FakeConverterContext> {

    private final static Converter<FakeConverterContext> TO_STRING_CONVERTER = Converters.mapper(
            (s) -> s instanceof Integer,
            (t) -> t == String.class,
            (v) -> {
                final int integer = (Integer) v + 1;
                return String.valueOf((char) integer);
            }
    );

    @Test
    public void testWithNullConverterFails() {
        assertThrows(
                NullPointerException.class,
                () -> ConverterConverterStringCharacter.with(null)
        );
    }

    @Test
    public void testWithConverterConverterStringCharacter() {
        final ConverterConverterStringCharacter<?> converter = this.createConverter();

        assertSame(
                converter,
                ConverterConverterStringCharacter.with(converter)
        );
    }

    @Test
    public void testConvertUnsupportedCharacterTypeFails() {
        this.convertFails(
                this,
                Character.class
        );
    }

    @Test
    public void testConvertInvalidStringToCharacterFails() {
        this.convertFails(
                "ABC123",
                Character.class
        );
    }

    @Test
    public void testConvertUnsupportedTypeStringFail2() {
        this.convertFails(
                this,
                String.class
        );
    }

    @Test
    public void testConvertNullToCharacter() {
        this.convertAndCheck(
                null,
                Character.class,
                null
        );
    }

    @Test
    public void testConvertNullToString() {
        this.convertAndCheck(
                null,
                String.class,
                null
        );
    }

    @Test
    public void testConvertCharacterToCharacter() {
        this.convertAndCheck(
                'A',
                Character.class
        );
    }

    @Test
    public void testConvertStringToString() {
        this.convertAndCheck(
                "ABC123",
                String.class
        );
    }

    @Test
    public void testConvertStringToCharacter() {
        this.convertAndCheck(
                "Z",
                Character.class,
                'Z'
        );
    }

    @Test
    public void testConvertToCharacter() {
        this.convertAndCheck(
                Integer.valueOf('A'),
                Character.class,
                'B'
        );
    }

    @Test
    public void testConvertToString() {
        this.convertAndCheck(
                (int)'A',
                String.class,
                "B"
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createConverter(),
                TO_STRING_CONVERTER + "->Character|String"
        );
    }

    // TypeNameTesting...................................................................................................

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ConverterConverterStringCharacter<FakeConverterContext> createConverter() {
        return ConverterConverterStringCharacter.with(
                TO_STRING_CONVERTER
        );
    }

    @Override
    public FakeConverterContext createContext() {
        return new FakeConverterContext();
    }

    @Override
    public Class<ConverterConverterStringCharacter<FakeConverterContext>> type() {
        return Cast.to(ConverterConverterStringCharacter.class);
    }
}
