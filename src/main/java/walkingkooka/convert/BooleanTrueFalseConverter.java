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
final class BooleanTrueFalseConverter<T, C extends ConverterContext> extends Converter2<C> {

    static <D, C extends ConverterContext> BooleanTrueFalseConverter<D, C> with(final Predicate<Object> source,
                                                                                final Predicate<Object> falseValue,
                                                                                final Predicate<Class<?>> target,
                                                                                final D trueAnswer,
                                                                                final D falseAnswer) {
        Objects.requireNonNull(source, "source");
        Objects.requireNonNull(falseValue, "falseValue");
        Objects.requireNonNull(target, "target");
        Objects.requireNonNull(trueAnswer, "trueAnswer");
        Objects.requireNonNull(falseAnswer, "falseAnswer");

        return new BooleanTrueFalseConverter<>(source,
                falseValue,
                target,
                trueAnswer,
                falseAnswer);
    }

    private BooleanTrueFalseConverter(final Predicate<Object> source,
                                      final Predicate<Object> falseValue,
                                      final Predicate<Class<?>> target,
                                      final T trueAnswer,
                                      final T falseAnswer) {
        super();
        this.source = source;
        this.falseValue = falseValue;
        this.target = target;
        this.trueAnswer = trueAnswer;
        this.falseAnswer = falseAnswer;
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return this.source.test(value) &&
                this.target.test(type);
    }

    private final Predicate<Object> source;
    private final Predicate<Class<?>> target;

    @Override
    <T> Either<T, String> convert0(final Object value,
                                   final Class<T> type,
                                   final ConverterContext context) {
        return Either.left(Cast.to(this.convert2(value)));
    }

    private T convert2(final Object value) {
        return this.falseValue.test(value) ?
                this.falseAnswer :
                this.trueAnswer;
    }

    private final Predicate<Object> falseValue;
    private final T trueAnswer;
    private final T falseAnswer;

    @Override
    public String toString() {
        return this.source + "->" + this.target;
    }
}
