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
import walkingkooka.props.Properties;
import walkingkooka.props.PropertiesPath;

public final class ConverterTextToPropertiesTest implements ConverterTesting2<ConverterTextToProperties<FakeConverterContext>, FakeConverterContext> {

    @Test
    public void testConvertNullToPropertiesFails() {
        this.convertFails(
            null,
            Properties.class
        );
    }

    @Test
    public void testConvertStringToProperties() {
        final Properties properties = Properties.EMPTY.set(
            PropertiesPath.parse("hello"),
            "World"
        );

        this.convertAndCheck(
            properties.text(),
            Properties.class,
            properties
        );
    }

    @Override
    public ConverterTextToProperties<FakeConverterContext> createConverter() {
        return ConverterTextToProperties.instance();
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
            "text to Properties"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToProperties<FakeConverterContext>> type() {
        return Cast.to(ConverterTextToProperties.class);
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
