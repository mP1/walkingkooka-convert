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

public final class ConverterCharacterStringConverterTest implements ConverterTesting2<ConverterCharacterStringConverter<FakeConverterContext>, FakeConverterContext> {

    private final static Converter<FakeConverterContext> STRING_TO_NUMBER = Converters.mapper(
            (s) -> s instanceof String,
            (t) -> t == Integer.class,
            (s) -> Integer.parseInt((String) s)
    );

    @Test
    public void testWithNullConverterFails() {
        assertThrows(
                NullPointerException.class,
                () -> ConverterCharacterStringConverter.with(null)
        );
    }

    @Test
    public void testWithConverterCharacterStringConverter() {
        final ConverterCharacterStringConverter<?> converter = this.createConverter();

        assertSame(
                converter,
                ConverterCharacterStringConverter.with(converter)
        );
    }

    @Test
    public void testConvertNonCharacterFails() {
        this.convertFails(
                "Not a character",
                Integer.class
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

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createConverter(),
                "Character->" + STRING_TO_NUMBER
        );
    }

    @Override
    public ConverterCharacterStringConverter<FakeConverterContext> createConverter() {
        return ConverterCharacterStringConverter.with(
                STRING_TO_NUMBER
        );
    }

    @Override
    public FakeConverterContext createContext() {
        return new FakeConverterContext();
    }

    @Override
    public Class<ConverterCharacterStringConverter<FakeConverterContext>> type() {
        return Cast.to(ConverterCharacterStringConverter.class);
    }
}
