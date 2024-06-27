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
import walkingkooka.text.HasText;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ChainConverterTest implements ConverterTesting2<ChainConverter<FakeConverterContext>, FakeConverterContext> {

    @Test
    public void testWithNullFirstConverterFails() {
        assertThrows(
                NullPointerException.class,
                () -> ChainConverter.with(
                        null,
                        String.class,
                        Converters.stringToCharacter()
                )
        );
    }

    @Test
    public void testWithNullIntermediateTypeFails() {
        assertThrows(
                NullPointerException.class,
                () -> ChainConverter.with(
                        Converters.hasTextToString(),
                        null,
                        Converters.stringToCharacter()
                )
        );
    }

    @Test
    public void testWithNullSecondConverterFails() {
        assertThrows(
                NullPointerException.class,
                () -> ChainConverter.with(
                        Converters.hasTextToString(),
                        String.class,
                        null
                )
        );
    }

    // convert..........................................................................................................

    @Test
    public void testConvertNullSuccess() {
        this.convertAndCheck(
                ChainConverter.with(
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
                ChainConverter.with(
                        Converters.numberToBoolean(),
                        String.class,
                        Converters.stringToCharacter()
                ),
                BigDecimal.TEN,
                Character.class
        );
    }

    @Test
    public void testConvertSecondConverterFails() {
        this.convertFails(
                ChainConverter.with(
                        Converters.objectToString(),
                        String.class,
                        Converters.stringToCharacter()
                ),
                123,
                Character.class
        );
    }

    @Override
    public ChainConverter<FakeConverterContext> createConverter() {
        return ChainConverter.with(
                Converters.hasTextToString(),
                String.class,
                Converters.stringToCharacter()
        );
    }

    @Override
    public FakeConverterContext createContext() {
        return new FakeConverterContext();
    }

    // toString..........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
               this.createConverter(),
               "HasText to String to String to Character"
        );
    }

    // classTesting.....................................................................................................

    @Override
    public Class<ChainConverter<FakeConverterContext>> type() {
        return Cast.to(ChainConverter.class);
    }
}
