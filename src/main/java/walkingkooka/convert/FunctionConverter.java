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

import walkingkooka.Cast;
import walkingkooka.Either;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * A {@link Converter} passes the given value to a {@link Function} such as a method handle to a static method which performs the conversion.
 */
final class FunctionConverter<S, D, C extends ConverterContext> extends Converter2<C> {

    static <S, D, C extends ConverterContext> FunctionConverter<S, D, C> with(final Predicate<Object> source,
                                                                              final Predicate<Class<?>> target,
                                                                              final Function<S, D> converter) {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(target, "target");

        return new FunctionConverter<>(source, target, converter);
    }

    /**
     * Private ctor use static factory.
     */
    private FunctionConverter(final Predicate<Object> source,
                              final Predicate<Class<?>> target,
                              final Function<S, D> converter) {
        super();
        this.source = source;
        this.target = target;
        this.converter = converter;
    }

    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return this.source.test(value) &&
                this.target.test(type);
    }

    @Override <T> Either<T, String> convert0(final Object value,
                                             final Class<T> type,
                                             final ConverterContext context) {
        return Either.left(Cast.to(this.converter.apply(Cast.to(value))));
    }

    private final Predicate<Object> source;
    private final Predicate<Class<?>> target;
    private final Function<S, D> converter;

    @Override
    public String toString() {
        return this.source + "->" + this.target;
    }
}
