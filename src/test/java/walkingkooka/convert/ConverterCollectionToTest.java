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
import walkingkooka.collect.list.Lists;

import java.util.Collections;
import java.util.Optional;

public final class ConverterCollectionToTest extends ConverterTestCase2<ConverterCollectionTo<ConverterContext>> {

    @Test
    public void testConvertWithNullAndInvalidValueFails() {
        this.convertFails(
            null,
            Void.class
        );
    }

    @Test
    public void testConvertWithInvalidListValueFails() {
        this.convertFails(
            Optional.of("Invalid"),
            Number.class
        );
    }

    @Test
    public void testConvertWithListTwoElementsFails() {
        this.convertFails(
            Lists.of(
                111,
                222
            ),
            Number.class
        );
    }

    @Test
    public void testConvertWithSetTwoElementsFails() {
        this.convertFails(
            Lists.of(
                333,
                444
            ),
            Number.class
        );
    }

    @Test
    public void testConvertWithNull() {
        this.convertAndCheck(
            null,
            Number.class
        );
    }

    @Test
    public void testConvertWithEmptyList() {
        this.convertAndCheck(
            Collections.emptyList(),
            Number.class,
            null
        );
    }

    @Test
    public void testConvertWithEmptySet() {
        this.convertAndCheck(
            Collections.emptySet(),
            Number.class,
            null
        );
    }

    @Test
    public void testConvertWithListOneElement() {
        this.convertAndCheck(
            Lists.of(123),
            Integer.class,
            123
        );
    }

    @Test
    public void testConvertWithSetOneElement() {
        this.convertAndCheck(
            Lists.of(123),
            Integer.class,
            123
        );
    }

    @Override
    public ConverterCollectionTo<ConverterContext> createConverter() {
        return ConverterCollectionTo.instance();
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
            "Collection to"
        );
    }

    // Class............................................................................................................

    @Override
    public Class<ConverterCollectionTo<ConverterContext>> type() {
        return Cast.to(ConverterCollectionTo.class);
    }
}
