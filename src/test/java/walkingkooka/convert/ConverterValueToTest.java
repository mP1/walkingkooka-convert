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
import walkingkooka.HasValue;

import java.util.Optional;

public final class ConverterValueToTest extends ConverterTestCase2<ConverterValueTo<ConverterContext>> {

    @Test
    public void testConvertWithNull() {
        this.convertAndCheck(
            null,
            Number.class
        );
    }

    @Test
    public void testConvertWithOptional() {
        this.convertFails(
            Optional.empty(),
            Object.class
        );
    }

    @Test
    public void testConvertWithValue() {
        final Long longValue = 123L;

        this.convertAndCheck(
            new HasValue<>() {

                @Override
                public Long value() {
                    return longValue;
                }
            },
            Integer.class,
            longValue.intValue()
        );
    }

    @Override
    public ConverterValueTo<ConverterContext> createConverter() {
        return ConverterValueTo.instance();
    }

    @Override
    public ConverterContext createContext() {
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

            private final Converter<FakeConverterContext> converter = Converters.numberToNumber();
        };
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "HasValue to"
        );
    }

    // Class............................................................................................................

    @Override
    public Class<ConverterValueTo<ConverterContext>> type() {
        return Cast.to(ConverterValueTo.class);
    }
}
