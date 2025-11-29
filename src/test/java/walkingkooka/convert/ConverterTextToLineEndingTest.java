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
import walkingkooka.text.LineEnding;

public final class ConverterTextToLineEndingTest implements ConverterTesting2<ConverterTextToLineEnding<FakeConverterContext>, FakeConverterContext> {

    @Test
    public void testConvertNullFails() {
        this.convertFails(
            null,
            LineEnding.class
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
    public void testConvertBackslashRToLineEnding() {
        this.convertAndCheck(
            "\r",
            LineEnding.class,
            LineEnding.CR
        );
    }

    @Test
    public void testConvertBackslashCrBackslashNToLineEnding() {
        this.convertAndCheck(
            "\r\n",
            LineEnding.class,
            LineEnding.CRNL
        );
    }

    @Test
    public void testConvertBackslashNToLineEnding() {
        this.convertAndCheck(
            "\n",
            LineEnding.class,
            LineEnding.NL
        );
    }

    @Test
    public void testConvertCRUpperCaseToLineEnding() {
        this.convertAndCheck(
            "CR",
            LineEnding.class,
            LineEnding.CR
        );
    }

    @Test
    public void testConvertCRLFUpperCaseToLineEnding() {
        this.convertAndCheck(
            "CRLF",
            LineEnding.class,
            LineEnding.CRNL
        );
    }

    @Test
    public void testConvertCRNLUpperCaseToLineEnding() {
        this.convertAndCheck(
            "CRNL",
            LineEnding.class,
            LineEnding.CRNL
        );
    }

    @Test
    public void testConvertLFUpperCaseToLineEnding() {
        this.convertAndCheck(
            "LF",
            LineEnding.class,
            LineEnding.NL
        );
    }
    
    @Test
    public void testConvertNLUpperCaseToLineEnding() {
        this.convertAndCheck(
            "NL",
            LineEnding.class,
            LineEnding.NL
        );
    }

    @Test
    public void testConvertCRLowerCaseToLineEnding() {
        this.convertAndCheck(
            "cr",
            LineEnding.class,
            LineEnding.CR
        );
    }

    @Test
    public void testConvertCRLFLowerCaseToLineEnding() {
        this.convertAndCheck(
            "crlf",
            LineEnding.class,
            LineEnding.CRNL
        );
    }

    @Test
    public void testConvertLFLowerCaseToLineEnding() {
        this.convertAndCheck(
            "lf",
            LineEnding.class,
            LineEnding.NL
        );
    }

    @Test
    public void testConvertCRNLLowerCaseToLineEnding() {
        this.convertAndCheck(
            "crnl",
            LineEnding.class,
            LineEnding.CRNL
        );
    }

    @Test
    public void testConvertNLLowerCaseToLineEnding() {
        this.convertAndCheck(
            "nl",
            LineEnding.class,
            LineEnding.NL
        );
    }

    @Override
    public ConverterTextToLineEnding<FakeConverterContext> createConverter() {
        return ConverterTextToLineEnding.instance();
    }

    @Override
    public FakeConverterContext createContext() {
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

            private final Converter<FakeConverterContext> converter = Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString();
        };
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "text to LineEnding"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToLineEnding<FakeConverterContext>> type() {
        return Cast.to(ConverterTextToLineEnding.class);
    }

    @Override
    public String typeNamePrefix() {
        return Converter.class.getSimpleName();
    }

    @Override
    public String typeNameSuffix() {
        return "";
    }
}
