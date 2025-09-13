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
import java.util.function.Predicate;

/**
 * A {@link Converter} that knows how to convert towards a boolean answer.
 */
final class ToBooleanConverter<V, C extends ConverterContext> implements ShortCircuitingConverter<C> {

    static <V, C extends ConverterContext> ToBooleanConverter<V, C> with(final Predicate<Object> source,
                                                                         final Predicate<Class<?>> target,
                                                                         final Predicate<Object> trueValue,
                                                                         final V trueAnswer,
                                                                         final V falseAnswer) {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(target, "target");
        Objects.requireNonNull(trueValue, "trueValue");

        return new ToBooleanConverter<>(
            source,
            target,
            trueValue,
            trueAnswer,
            falseAnswer
        );
    }

    private ToBooleanConverter(final Predicate<Object> source,
                               final Predicate<Class<?>> target,
                               final Predicate<Object> trueValue,
                               final V trueAnswer,
                               final V falseAnswer) {
        super();
        this.source = source;
        this.target = target;
        this.trueValue = trueValue;
        this.trueAnswer = Either.left(trueAnswer);
        this.falseAnswer = Either.left(falseAnswer);
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return (value == null || this.source.test(value)) &&
            this.target.test(type);
    }

    private final Predicate<Object> source;
    private final Predicate<Class<?>> target;

    @Override
    public <T> Either<T, String> doConvert(final Object value,
                                           final Class<T> type,
                                           final C context) {
        return Cast.to(
            this.trueValue.test(value) ?
                this.trueAnswer :
                this.falseAnswer
        );
    }

    private final Predicate<Object> trueValue;
    private final Either<V, String> trueAnswer;
    private final Either<V, String> falseAnswer;

    @Override
    public String toString() {
        return this.source + " to " + this.target;
    }
}
