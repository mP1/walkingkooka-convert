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
import walkingkooka.HashCodeEqualsDefinedTesting2;
import walkingkooka.text.HasText;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ConverterChainTest extends ConverterTestCase2<ConverterChain<ConverterContext>>
    implements HashCodeEqualsDefinedTesting2<ConverterChain<ConverterContext>> {

    @Test
    public void testWithNullFirstConverterFails() {
        assertThrows(
            NullPointerException.class,
            () -> ConverterChain.with(
                null,
                String.class,
                Converters.stringToCharacterOrString()
            )
        );
    }

    @Test
    public void testWithNullIntermediateTypeFails() {
        assertThrows(
            NullPointerException.class,
            () -> ConverterChain.with(
                Converters.hasText(),
                null,
                Converters.stringToCharacterOrString()
            )
        );
    }

    @Test
    public void testWithNullSecondConverterFails() {
        assertThrows(
            NullPointerException.class,
            () -> ConverterChain.with(
                Converters.hasText(),
                String.class,
                null
            )
        );
    }

    // convert..........................................................................................................

    @Test
    public void testConvertNullSuccess() {
        this.convertAndCheck(
            ConverterChain.with(
                Converters.simple(),
                Void.class,
                Converters.simple()
            ),
            null,
            Void.class,
            null
        );
    }

    @Test
    public void testConvertSuccess() {
        this.convertAndCheck(
            new HasText() {
                @Override
                public String text() {
                    return "A";
                }
            },
            'A'
        );
    }

    @Test
    public void testConvertFirstConverterFails() {
        this.convertFails(
            ConverterChain.with(
                Converters.numberToBoolean(),
                String.class,
                Converters.stringToCharacterOrString()
            ),
            BigDecimal.TEN,
            Character.class
        );
    }

    @Test
    public void testConvertSecondConverterFails() {
        this.convertFails(
            ConverterChain.with(
                Converters.objectToString(),
                String.class,
                Converters.stringToCharacterOrString()
            ),
            123,
            Character.class
        );
    }

    @Override
    public ConverterChain<ConverterContext> createConverter() {
        return ConverterChain.with(
            Converters.hasText(),
            String.class,
            Converters.stringToCharacterOrString()
        );
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    // hashCode/equals..................................................................................................

    @Test
    public void testEqualsDifferentFirstConverter() {
        this.checkNotEquals(
            ConverterChain.with(
                Converters.fake(),
                String.class,
                Converters.stringToCharacterOrString()
            )
        );
    }

    @Test
    public void testEqualsDifferentType2() {
        this.checkNotEquals(
            ConverterChain.with(
                Converters.booleanToNumber(),
                void.class,
                Converters.stringToCharacterOrString()
            )
        );
    }

    @Test
    public void testEqualsDifferentSecondConverter() {
        this.checkNotEquals(
            ConverterChain.with(
                Converters.booleanToNumber(),
                String.class,
                Converters.fake()
            )
        );
    }

    @Override
    public ConverterChain<ConverterContext> createObject() {
        return ConverterChain.with(
            Converters.booleanToNumber(),
            String.class,
            Converters.stringToCharacterOrString()
        );
    }

    // toString..........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck2(
            "HasText to String",
            "String to Character",
            "HasText to String to Character"
        );
    }

    @Test
    public void testToString1() {
        this.toStringAndCheck2(
            "Magic",
            "String to Character",
            "Magic to String to Character"
        );
    }

    @Test
    public void testToString2() {
        this.toStringAndCheck2(
            "HasText to String",
            "Magic",
            "HasText to String to Magic"
        );
    }

    private void toStringAndCheck2(final String firstToString,
                                   final String secondToString,
                                   final String expected) {
        this.toStringAndCheck(
            ConverterChain.with(
                Converters.fake().setToString(firstToString),
                String.class,
                Converters.fake().setToString(secondToString)
            ),
            expected
        );
    }

    // classTesting.....................................................................................................

    @Override
    public Class<ConverterChain<ConverterContext>> type() {
        return Cast.to(ConverterChain.class);
    }
}
