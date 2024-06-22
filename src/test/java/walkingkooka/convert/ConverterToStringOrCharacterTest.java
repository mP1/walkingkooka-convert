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

public final class ConverterToStringOrCharacterTest implements ConverterTesting2<ConverterToStringOrCharacter<FakeConverterContext>, FakeConverterContext> {

    private final static Character NULL_TO_CHARACTER = '!';
    private final static String NULL_TO_STRING = NULL_TO_CHARACTER.toString();

    private final static Converter<FakeConverterContext> INTEGER_TO_STRING_CONVERTER = Converters.mapper(
            (s) -> s instanceof Integer,
            (t) -> t == String.class,
            (v) -> {
                final Integer integer = (Integer) v;
                if (null == integer) {
                    return NULL_TO_STRING;
                }

                return String.valueOf(
                        (char) (integer + 1)
                );
            }
    );

    @Test
    public void testWithNullConverterFails() {
        assertThrows(
                NullPointerException.class,
                () -> ConverterToStringOrCharacter.with(null)
        );
    }

    @Test
    public void testWithConverterConverterStringCharacter() {
        final ConverterToStringOrCharacter<?> converter = this.createConverter();

        assertSame(
                converter,
                ConverterToStringOrCharacter.with(converter)
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
                NULL_TO_CHARACTER
        );
    }

    @Test
    public void testConvertNullToString() {
        this.convertAndCheck(
                null,
                String.class,
                NULL_TO_STRING
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
    public void testConvertCharacterToCharacter2() {
        this.convertAndCheck(
                ConverterToStringOrCharacter.with(Converters.fake()),
                'A',
                Character.class,
                'A'
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
    public void testConvertStringToString2() {
        this.convertAndCheck(
                ConverterToStringOrCharacter.with(Converters.fake()),
                "ABC123",
                String.class,
                "ABC123"
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
    public void testConvertStringToCharacter2() {
        this.convertAndCheck(
                ConverterToStringOrCharacter.with(Converters.fake()),
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
                INTEGER_TO_STRING_CONVERTER + "->Character|String"
        );
    }

    // TypeNameTesting...................................................................................................

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ConverterToStringOrCharacter<FakeConverterContext> createConverter() {
        return ConverterToStringOrCharacter.with(
                INTEGER_TO_STRING_CONVERTER
        );
    }

    @Override
    public FakeConverterContext createContext() {
        return new FakeConverterContext();
    }

    @Override
    public Class<ConverterToStringOrCharacter<FakeConverterContext>> type() {
        return Cast.to(ConverterToStringOrCharacter.class);
    }
}
