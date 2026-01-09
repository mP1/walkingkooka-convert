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
import walkingkooka.text.LineEnding;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.Locale;

public final class ConverterCharacterOrStringToStringTest implements ConverterTesting2<ConverterCharacterOrStringToString<FakeConverterContext>, FakeConverterContext> {

    private final static Integer NULL_VALUE = 999;

    @Test
    public void testConvertNonCharacterFails() {
        this.convertFails(
            777,
            Integer.class
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
    public void testConvertNullToString() {
        this.convertAndCheck(
            null,
            String.class
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
    public void testConvertStringToString() {
        this.convertAndCheck(
            "ABC123",
            String.class,
            "ABC123"
        );
    }

    @Override
    public ConverterCharacterOrStringToString<FakeConverterContext> createConverter() {
        return ConverterCharacterOrStringToString.instance();
    }

    @Override
    public FakeConverterContext createContext() {
        return new FakeConverterContext();
    }

    // ConverterChain...................................................................................................

    @Test
    public void testChainConverterCharacterToStringToNumber() {
        final Converter<ConverterContext> converter = Converters.chain(
            Converters.characterOrStringToString(),
            String.class,
            Converters.stringToNumber(
                (x) -> (DecimalFormat) DecimalFormat.getInstance(Locale.forLanguageTag("EN-AU"))
            )
        );

        this.convertAndCheck(
            converter,
            '5',
            Number.class,
            ConverterContexts.basic(
                false, // canNumbersHaveGroupSeparator
                0, // dateOffset
                LineEnding.NL,
                ',', // valueSeparator
                Converters.fake(),
                DateTimeContexts.fake(),
                DecimalNumberContexts.american(MathContext.DECIMAL32)
            ),
            BigDecimal.valueOf(5)
        );

        this.toStringAndCheck(
            converter,
            "Character or String to String to DecimalFormat String to Number"
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "Character or String to String"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterCharacterOrStringToString<FakeConverterContext>> type() {
        return Cast.to(ConverterCharacterOrStringToString.class);
    }

    // TypeNaming.......................................................................................................

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }
}
