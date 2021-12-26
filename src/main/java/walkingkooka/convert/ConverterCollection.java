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

import walkingkooka.Either;
import walkingkooka.collect.list.Lists;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * A {@link Converter} which tries all collectors to satisfy a request.
 */
final class ConverterCollection<C extends ConverterContext> implements Converter<C> {

    /**
     * Factory that creates a {@link ConverterCollection} if more than one converter is given.
     * Providing zero will result in an {@link IllegalArgumentException}.
     */
    static <C extends ConverterContext> Converter<C> with(final List<Converter<C>> converters) {
        Objects.requireNonNull(converters, "converters");

        final List<Converter<C>> copy = Lists.immutable(converters);

        Converter<C> result;
        final int count = copy.size();
        switch (count) {
            case 0:
                throw new IllegalArgumentException("Expected at least 1 converter but got 0");
            case 1:
                result = copy.get(0);
                break;
            default:
                result = new ConverterCollection<>(copy);
                break;
        }

        return result;
    }

    private ConverterCollection(final List<Converter<C>> converters) {
        this.converters = converters;
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return null == value || this.converters.stream()
                .anyMatch(c -> c.canConvert(value, type, context));
    }

    @Override
    public <T> Either<T, String> convert(final Object value,
                                         final Class<T> type,
                                         final C context) {
        return this.canConvert(value, type, context) ?
                null == value ?
                        Either.left(null) :
                        this.convertNonNull(value, type, context) :
                this.failConversion(value, type);
    }

    private <T> Either<T, String> convertNonNull(final Object value,
                                                 final Class<T> type,
                                                 final C context) {
        Either<T, String> result = null;

        for (final Converter<C> possible : this.converters) {
            if (possible.canConvert(value, type, context)) {
                result = possible.convert(value, type, context);
                if (result.isLeft()) {
                    break;
                }
                // try again.
            }
        }

        if (null == result) {
            result = this.failConversion(value, type);
        }
        return result;
    }

    private final List<Converter<C>> converters;

    @Override
    public String toString() {
        return this.converters.stream()
                .map(Object::toString)
                .collect(Collectors.joining(" | "));
    }
}
