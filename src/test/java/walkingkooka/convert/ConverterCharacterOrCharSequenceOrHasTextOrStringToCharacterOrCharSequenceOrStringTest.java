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
import walkingkooka.datetime.DateTimeContexts;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.naming.Name;
import walkingkooka.naming.Names;
import walkingkooka.naming.StringName;
import walkingkooka.text.HasText;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Optional;

public final class ConverterCharacterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrStringTest extends ConverterTestCase2<ConverterCharacterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString<ConverterContext>> {

    // character.........................................................................................................

    @Test
    public void testConvertNullToCharacter() {
        this.convertAndCheck(
            null,
            Character.class
        );
    }

    @Test
    public void testConvertEmptyCharSequenceToCharacterFails() {
        this.convertFails(
            charSequence(""),
            Character.class
        );
    }

    @Test
    public void testConvertCharSequenceToCharacterFails() {
        this.convertAndCheck(
            charSequence("a"),
            Character.class,
            'a'
        );
    }

    @Test
    public void testConvertCharSequenceToCharacterFails2() {
        this.convertFails(
            charSequence("abc"),
            Character.class
        );
    }

    @Test
    public void testConvertEmptyHasTextToCharacterFails() {
        this.convertFails(
            hasText(""),
            Character.class
        );
    }

    @Test
    public void testConvertHasTextToCharacterFails() {
        this.convertAndCheck(
            hasText("a"),
            Character.class,
            'a'
        );
    }

    @Test
    public void testConvertHasTextToCharacterFails2() {
        this.convertFails(
            hasText("abc"),
            Character.class
        );
    }

    @Test
    public void testConvertEmptyStringToCharacterFails() {
        this.convertFails(
            "",
            Character.class
        );
    }

    @Test
    public void testConvertStringToCharacterFails() {
        this.convertAndCheck(
            "a",
            Character.class,
            'a'
        );
    }

    @Test
    public void testConvertStringToCharacterFails2() {
        this.convertFails(
            "abc",
            Character.class
        );
    }

    @Test
    public void testConvertNameToCharacterFails() {
        this.convertFails(
            Names.string("Fails123"),
            Character.class
        );
    }

    // CharSequence.....................................................................................................

    @Test
    public void testConvertNullToCharSequence() {
        this.convertAndCheck(
            null,
            CharSequence.class
        );
    }

    @Test
    public void testConvertEmptyCharSequenceToCharSequence() {
        this.convertAndCheck(
            charSequence(""),
            CharSequence.class
        );
    }

    @Test
    public void testConvertCharSequenceToCharSequence() {
        this.convertAndCheck(
            charSequence("a"),
            CharSequence.class
        );
    }

    @Test
    public void testConvertCharSequenceToCharSequence2() {
        this.convertAndCheck(
            charSequence("abc"),
            CharSequence.class
        );
    }

    @Test
    public void testConvertEmptyHasTextToCharSequence() {
        this.convertAndCheck(
            hasText(""),
            CharSequence.class,
            ""
        );
    }

    @Test
    public void testConvertHasTextToCharSequence() {
        this.convertAndCheck(
            hasText("a"),
            CharSequence.class,
            "a"
        );
    }

    @Test
    public void testConvertHasTextToCharSequence2() {
        this.convertAndCheck(
            hasText("abc"),
            CharSequence.class,
            "abc"
        );
    }

    @Test
    public void testConvertEmptyStringToCharSequence() {
        this.convertAndCheck(
            "",
            CharSequence.class
        );
    }

    @Test
    public void testConvertStringToCharSequence2() {
        this.convertAndCheck(
            "a",
            CharSequence.class
        );
    }

    @Test
    public void testConvertStringToCharSequence3() {
        this.convertAndCheck(
            "abc",
            CharSequence.class
        );
    }

    @Test
    public void testConvertNameToCharSequence() {
        final StringName name = Names.string("Fails123");

        this.convertAndCheck(
            name,
            CharSequence.class,
            name.text()
        );
    }

    // CharSequence.....................................................................................................

    @Test
    public void testConvertNullToHasTextFails() {
        this.convertFails(
            null,
            HasText.class
        );
    }

    @Test
    public void testConvertCharacterToHasText() {
        this.convertFails(
            'A',
            HasText.class
        );
    }

    @Test
    public void testConvertCharSequenceToHasText() {
        this.convertFails(
            charSequence("Abc"),
            HasText.class
        );
    }

    @Test
    public void testConvertHasTextToHasTextFails() {
        this.convertFails(
            hasText("Abc"),
            HasText.class
        );
    }

    @Test
    public void testConvertStringToHasText() {
        this.convertFails(
            "Abc",
            HasText.class
        );
    }

    @Test
    public void testConvertNameToHasTextFails() {
        this.convertFails(
            Names.string("Fails123"),
            HasText.class
        );
    }

    // String...........................................................................................................

    @Test
    public void testConvertNullToString() {
        this.convertAndCheck(
            null,
            String.class
        );
    }

    @Test
    public void testConvertEmptyCharSequenceToString() {
        this.convertAndCheck(
            charSequence(""),
            String.class,
            ""
        );
    }

    @Test
    public void testConvertCharSequenceToString() {
        this.convertAndCheck(
            charSequence("a"),
            String.class,
            "a"
        );
    }

    @Test
    public void testConvertCharSequenceToString2() {
        this.convertAndCheck(
            charSequence("abc"),
            String.class,
            "abc"
        );
    }

    @Test
    public void testConvertEmptyHasTextToString() {
        this.convertAndCheck(
            hasText(""),
            String.class,
            ""
        );
    }

    @Test
    public void testConvertHasTextToString() {
        this.convertAndCheck(
            hasText("a"),
            String.class,
            "a"
        );
    }

    @Test
    public void testConvertHasTextToString2() {
        this.convertAndCheck(
            hasText("abc"),
            String.class,
            "abc"
        );
    }

    @Test
    public void testConvertEmptyStringToString() {
        this.convertAndCheck(
            "",
            String.class
        );
    }

    @Test
    public void testConvertStringToString2() {
        this.convertAndCheck(
            "a",
            String.class
        );
    }

    @Test
    public void testConvertStringToString3() {
        this.convertAndCheck(
            "abc",
            String.class
        );
    }

    @Test
    public void testConvertNameToStringFails() {
        final String name = "Name123";

        this.convertAndCheck(
            Names.string(name),
            String.class,
            name
        );
    }

    @Test
    public void testConvertNameToNameFails() {
        this.convertFails(
            Names.string("Fails123"),
            Name.class
        );
    }

    private static CharSequence charSequence(final String text) {
        return new StringBuilder(text);
    }

    private static HasText hasText(final String text) {
        return new HasText() {

            @Override
            public String text() {
                return text;
            }
        };
    }

    @Override
    public ConverterCharacterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString<ConverterContext> createConverter() {
        return ConverterCharacterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }


    // ConverterChain...................................................................................................

    @Test
    public void testChainConverterNumberToStringToCharacter() {
        final Converter<ConverterContext> converter = Converters.chain(
            Converters.numberToString(
                (x) -> (DecimalFormat) DecimalFormat.getInstance(Locale.forLanguageTag("EN-AU"))
            ),
            String.class,
            Converters.stringToCharacterOrString()
        );

        this.convertAndCheck(
            converter,
            BigDecimal.valueOf(5),
            Character.class,
            ConverterContexts.basic(
                (l) -> Optional.of(
                    Currency.getInstance(l)
                ), // canCurrencyForLocale
                (l) -> {
                    throw new UnsupportedOperationException();
                }, // canDateTimeSymbolsForLocale
                (l) -> {
                    throw new UnsupportedOperationException();
                }, // canDecimalNumberSymbolsForLocale
                (lt) -> {
                    throw new UnsupportedOperationException();
                }, // canLocaleForLanguageTag
                false, // canNumbersHaveGroupSeparator
                0, // dateTimeOffset
                Indentation.SPACES2,
                LineEnding.NL,
                ',', // valueSeparator
                Converters.fake(),
                DateTimeContexts.fake(),
                DecimalNumberContexts.american(MathContext.DECIMAL32)
            ),
            '5'
        );

        this.toStringAndCheck(
            converter,
            "DecimalFormat Number to String to Character or String"
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "Character or CharSequence or HasText or String to Character or CharSequence or String"
        );
    }

    // Class............................................................................................................

    @Override
    public Class<ConverterCharacterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString<ConverterContext>> type() {
        return Cast.to(ConverterCharacterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString.class);
    }
}
