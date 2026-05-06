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
import walkingkooka.Either;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;

public final class ConverterTextToIndentationTest extends ConverterTextToTestCase<ConverterTextToIndentation<ConverterContext>, Indentation> {

    @Test
    public void testConvertNullFails() {
        this.convertFails(
            null,
            Indentation.class
        );
    }

    @Test
    public void testConvertInvalidStringToLineEndingFails() {
        this.convertFails(
            "Invalid!",
            LineEnding.class
        );
    }

    @Test
    public void testConvertStringToIndentationTwoSpaces() {
        final String spaces = "  ";
        this.convertAndCheck(
            spaces,
            Indentation.class,
            Indentation.with(spaces)
        );
    }

    @Test
    public void testConvertStringToIndentationFourSpaces() {
        final String spaces = "    ";
        this.convertAndCheck(
            spaces,
            Indentation.class,
            Indentation.with(spaces)
        );
    }

    @Override
    public ConverterTextToIndentation<ConverterContext> createConverter() {
        return ConverterTextToIndentation.instance();
    }

    @Override
    public ConverterContext createContext() {
        return new FakeConverterContext() {

            @Override
            public boolean canConvert(final Object value,
                                      final Class<?> type) {
                return this.converter.canConvert(
                    value,
                    type,
                    this
                );
            }

            @Override
            public <T> Either<T, String> convert(final Object value,
                                                 final Class<T> target) {
                return this.converter.convert(
                    value,
                    target,
                    this
                );
            }

            private final Converter<ConverterContext> converter = Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString();
        };
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "text to Indentation"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToIndentation<ConverterContext>> type() {
        return Cast.to(ConverterTextToIndentation.class);
    }
}
