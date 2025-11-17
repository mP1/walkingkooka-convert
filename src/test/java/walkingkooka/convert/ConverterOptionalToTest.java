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

import java.util.Optional;

public final class ConverterOptionalToTest extends ConverterTestCase2<ConverterOptionalTo<ConverterContext>> {

    @Test
    public void testConvertWithNull() {
        this.convertAndCheck(
            null,
            Number.class
        );
    }

    @Test
    public void testConvertWithEmptyOptional() {
        this.convertAndCheck(
            Optional.empty(),
            Number.class,
            null
        );
    }

    @Test
    public void testConvertWithInvalidOptionalValueFails() {
        this.convertFails(
            Optional.of("Invalid"),
            Number.class
        );
    }

    @Test
    public void testConvertWithOptionalValue() {
        this.convertAndCheck(
            Optional.of(123),
            Integer.class,
            123
        );
    }

    @Test
    public void testConvertWithOptionalValue2() {
        this.convertAndCheck(
            Optional.of(456),
            Long.class,
            456L
        );
    }

    @Test
    public void testConvertWithOptionalOptionalValueFails() {
        this.convertFails(
            Optional.of(
                Optional.of(789)
            ),
            Integer.class
        );
    }

    @Override
    public ConverterOptionalTo<ConverterContext> createConverter() {
        return ConverterOptionalTo.instance();
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
            "Optional to"
        );
    }

    // Class............................................................................................................

    @Override
    public Class<ConverterOptionalTo<ConverterContext>> type() {
        return Cast.to(ConverterOptionalTo.class);
    }
}
