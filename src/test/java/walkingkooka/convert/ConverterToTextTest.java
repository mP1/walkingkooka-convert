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
import walkingkooka.collect.list.CsvStringList;
import walkingkooka.text.HasText;
import walkingkooka.text.LineEnding;

public final class ConverterToTextTest extends ConverterTestCase2<ConverterToText<ConverterContext>> {

    @Test
    public void testConvertNullToCharacter() {
        this.convertAndCheck(
            null,
            Character.class
        );
    }

    @Test
    public void testConvertNullToCharSequence() {
        this.convertAndCheck(
            null,
            CharSequence.class
        );
    }

    @Test
    public void testConvertNullToString() {
        this.convertAndCheck(
            null,
            String.class
        );
    }

    @Test
    public void testConvertCharacterToCharacter() {
        this.convertAndCheck(
            'A',
            Character.class,
            'A'
        );
    }

    @Test
    public void testConvertCharacterToCharSequence() {
        this.convertAndCheck(
            'A',
            CharSequence.class,
            "A"
        );
    }

    @Test
    public void testConvertCharacterToString() {
        this.convertAndCheck(
            'A',
            String.class,
            "A"
        );
    }

    @Test
    public void testConvertCharSequenceToCharacterFails() {
        this.convertFails(
            new StringBuilder("ABC"),
            Character.class
        );
    }

    @Test
    public void testConvertCharSequenceToCharSequence() {
        this.convertAndCheck(
            new StringBuilder("ABC"),
            CharSequence.class
        );
    }

    @Test
    public void testConvertCharSequenceToString() {
        this.convertAndCheck(
            new StringBuilder("ABC"),
            String.class,
            "ABC"
        );
    }

    @Test
    public void testConvertCharSequenceToCharacter() {
        this.convertAndCheck(
            new StringBuilder("A"),
            Character.class,
            'A'
        );
    }

    @Test
    public void testConvertStringEmptyStringToCharacterFails() {
        this.convertFails(
            "",
            Character.class
        );
    }

    @Test
    public void testConvertStringMoreThanOneCharacterStringToCharacterFails() {
        this.convertFails(
            "AB",
            Character.class
        );
    }

    @Test
    public void testConvertStringToCharacter() {
        final String string = "A";

        this.convertAndCheck(
            string,
            Character.class,
            'A'
        );
    }

    @Test
    public void testConvertStringToCharSequence() {
        final String string = "ABC";

        this.convertAndCheck(
            string,
            CharSequence.class,
            string
        );
    }

    @Test
    public void testConvertStringToString() {
        final String string = "ABC";

        this.convertAndCheck(
            string,
            String.class,
            string
        );
    }

    @Test
    public void testConvertHasTextToString() {
        final String text = "abc123";

        this.convertAndCheck(
            new HasText() {
                @Override
                public String text() {
                    return text;
                }
            },
            text
        );
    }

    @Test
    public void testConvertHasTextWithSeparatorToString() {
        final String text = "aa,bb,ccc";

        this.convertAndCheck(
            CsvStringList.parse(text),
            text.replace(',', ';')
        );
    }

    @Test
    public void testConvertLineEndingToString() {
        final LineEnding lineEnding = LineEnding.NL;

        this.convertAndCheck(
            lineEnding,
            lineEnding.text()
        );
    }

    @Override
    public ConverterToText<ConverterContext> createConverter() {
        return ConverterToText.instance();
    }

    @Override
    public ConverterContext createContext() {
        return new FakeConverterContext() {
            @Override
            public char valueSeparator() {
                return ';';
            }
        };
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "to Text"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterToText<ConverterContext>> type() {
        return Cast.to(ConverterToText.class);
    }
}
