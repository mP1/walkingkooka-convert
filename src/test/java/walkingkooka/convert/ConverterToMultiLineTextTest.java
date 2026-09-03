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
import walkingkooka.text.HasIndentationTesting;
import walkingkooka.text.HasLineEndingTesting;
import walkingkooka.text.HasMultiLineText;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;
import walkingkooka.text.MultiLineText;
import walkingkooka.text.TextContext;

public final class ConverterToMultiLineTextTest extends ConverterTestCase2<ConverterToMultiLineText<ConverterContext>>
    implements HasIndentationTesting,
    HasLineEndingTesting {

    @Test
    public void testConvertHasMultiValueLineToStringFails() {
        this.convertFails(
            MultiLineText.with("Hello"),
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
    public void testConvertNullToMultiLineText() {
        this.convertAndCheck(
            null,
            MultiLineText.class
        );
    }

    @Test
    public void testConvertEmptyStringToMultiLineText() {
        final String text = "";

        this.convertAndCheck(
            text,
            MultiLineText.with(text)
        );
    }

    @Test
    public void testConvertCharacterToMultiLineText() {
        final Character character = 'a';

        this.convertAndCheck(
            character,
            MultiLineText.with(
                character.toString()
            )
        );
    }

    @Test
    public void testConvertHasMultiLineTextToMultiLineText() {
        this.convertAndCheck(
            new HasMultiLineText() {
                @Override
                public MultiLineText multiLineText(final TextContext context) {
                    return MultiLineText.with(
                        context.indentation() +
                            "Hello" +
                            context.lineEnding()
                    );
                }
            },
            MultiLineText.with(
                "  Hello\n"
            )
        );
    }

    @Test
    public void testConvertHasMultiLineTextToMultiLineText2() {
        this.convertAndCheck(
            new HasMultiLineText() {
                @Override
                public MultiLineText multiLineText(final TextContext context) {
                    return MultiLineText.with(
                        context.indentation() +
                            "Hello" +
                            context.lineEnding()
                    );
                }
            },
            MultiLineText.class,
            this.createContext(
                DIFFERENT_INDENTATION,
                DIFFERENT_LINE_ENDING
            ),
            MultiLineText.with(
                "    Hello\r\n"
            )
        );
    }

    @Test
    public void testConvertMultiLineTextToMultiLineText() {
        final String text = "HelloWorldText123";

        this.convertAndCheck(
            MultiLineText.with(text),
            MultiLineText.with(text)
        );
    }

    @Override
    public ConverterToMultiLineText<ConverterContext> createConverter() {
        return ConverterToMultiLineText.instance();
    }

    @Override
    public ConverterContext createContext() {
        return this.createContext(
            INDENTATION,
            LINE_ENDING
        );
    }

    private ConverterContext createContext(final Indentation indentation,
                                           final LineEnding lineEnding) {
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

            @Override
            public Indentation indentation() {
                return indentation;
            }

            @Override
            public LineEnding lineEnding() {
                return lineEnding;
            }
        };
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "to MultiLineText"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterToMultiLineText<ConverterContext>> type() {
        return Cast.to(ConverterToMultiLineText.class);
    }
}
