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

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.Locale;

public final class ConverterStringToCharacterOrStringTest implements ConverterTesting2<ConverterStringToCharacterOrString<FakeConverterContext>, FakeConverterContext> {

    @Test
    public void testConvertNull() {
        this.convertAndCheck(
            null,
            Character.class
        );
    }

    @Test
    public void testConvertCharacterToCharacterFails() {
        this.convertFails(
            'A',
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
    public void testConvertStringLength2ToCharacterFails() {
        this.convertFails(
            "ab",
            Character.class
        );
    }

    @Test
    public void testConvertStringToCharacter() {
        this.convertAndCheck(
            "A",
            'A'
        );
    }

    @Test
    public void testConvertStringToString() {
        this.convertAndCheck(
            "",
            ""
        );
    }

    @Test
    public void testConvertStringToString2() {
        this.convertAndCheck(
            "abc123",
            "abc123"
        );
    }

    @Override
    public ConverterStringToCharacterOrString<FakeConverterContext> createConverter() {
        return ConverterStringToCharacterOrString.instance();
    }

    @Override
    public FakeConverterContext createContext() {
        return (FakeConverterContext) ConverterContexts.fake();
    }

    // ChainConverter...................................................................................................

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
                false, // canNumbersHaveGroupSeparator
                0,
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
            "String to Character or String"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterStringToCharacterOrString<FakeConverterContext>> type() {
        return Cast.to(ConverterStringToCharacterOrString.class);
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
