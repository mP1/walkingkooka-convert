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

public final class ConverterCharacterOrStringThenTest implements ConverterTesting2<ConverterCharacterOrStringThen<FakeConverterContext>, FakeConverterContext> {

    private final static Integer NULL_VALUE = 999;

    private final static Converter<FakeConverterContext> STRING_TO_INTEGER = Converters.mapper(
            (s) -> s instanceof String,
            (t) -> t == Integer.class,
            (s) -> null == s ? NULL_VALUE : Integer.parseInt((String) s)
    );

    @Test
    public void testWithNullConverterFails() {
        assertThrows(
                NullPointerException.class,
                () -> ConverterCharacterOrStringThen.with(null)
        );
    }

    @Test
    public void testWithConverterCharacterStringConverter() {
        final ConverterCharacterOrStringThen<?> converter = this.createConverter();

        assertSame(
                converter,
                ConverterCharacterOrStringThen.with(converter)
        );
    }

    @Test
    public void testConvertNonCharacterFails() {
        this.convertFails(
                777,
                Integer.class
        );
    }

    @Test
    public void testConvertCharacterToStringFails() {
        this.convertFails(
                'Q',
                String.class
        );
    }

    @Test
    public void testConvertStringToStringFails() {
        this.convertFails(
                "Hello",
                String.class
        );
    }

    @Test
    public void testConvertStringToCharacterFails() {
        this.convertFails(
                "Z",
                Character.class
        );
    }

    @Test
    public void testConvertUnsupportedTargetTypeFails() {
        this.convertFails(
                '1',
                Void.class
        );
    }

    @Test
    public void testConvertCharacterSupportedTargetType() {
        this.convertAndCheck(
                '1',
                Integer.class,
                1
        );
    }

    @Test
    public void testConvertCharacterSupportedTargetType2() {
        this.convertAndCheck(
                '2',
                Integer.class,
                2
        );
    }

    @Test
    public void testConvertStringSupportedTargetType2() {
        this.convertAndCheck(
                "3",
                Integer.class,
                3
        );
    }

    @Test
    public void testConvertNull() {
        this.convertAndCheck(
                null,
                Integer.class,
                NULL_VALUE
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createConverter(),
                "Character->" + STRING_TO_INTEGER
        );
    }

    @Override
    public ConverterCharacterOrStringThen<FakeConverterContext> createConverter() {
        return ConverterCharacterOrStringThen.with(
                STRING_TO_INTEGER
        );
    }

    @Override
    public FakeConverterContext createContext() {
        return new FakeConverterContext();
    }

    @Override
    public Class<ConverterCharacterOrStringThen<FakeConverterContext>> type() {
        return Cast.to(ConverterCharacterOrStringThen.class);
    }

    // TypeNaming.......................................................................................................

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }
}
