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

import java.time.ZoneOffset;

public final class ConverterTextToZoneOffsetTest implements ConverterTesting2<ConverterTextToZoneOffset<FakeConverterContext>, FakeConverterContext> {

    @Test
    public void testConvertNullFails() {
        this.convertFails(
            null,
            ZoneOffset.class
        );
    }

    @Test
    public void testConvertInvalidStringToZoneOffsetFails() {
        this.convertFails(
            "Invalid!",
            ZoneOffset.class
        );
    }

    @Test
    public void testConvertStringPlusDigitDigitToZoneOffset() {
        this.convertAndCheck(
            "+01",
            ZoneOffset.class,
            ZoneOffset.ofHours(1)
        );
    }

    @Test
    public void testConvertStringPlusDigitToZoneOffset() {
        this.convertAndCheck(
            "+2",
            ZoneOffset.class,
            ZoneOffset.ofHours(2)
        );
    }

    @Test
    public void testConvertStringMinusDigitToZoneOffset() {
        this.convertAndCheck(
            "-3",
            ZoneOffset.class,
            ZoneOffset.ofHours(-3)
        );
    }

    @Test
    public void testConvertStringPlusDigitDigitColonDigitDigitToZoneOffset() {
        this.convertAndCheck(
            "+04:00",
            ZoneOffset.class,
            ZoneOffset.ofHours(4)
        );
    }

    @Override
    public ConverterTextToZoneOffset<FakeConverterContext> createConverter() {
        return ConverterTextToZoneOffset.instance();
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
            "text to ZoneOffset"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToZoneOffset<FakeConverterContext>> type() {
        return Cast.to(ConverterTextToZoneOffset.class);
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
