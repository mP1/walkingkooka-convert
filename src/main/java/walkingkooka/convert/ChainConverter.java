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

/**
 * A {@link Converter} that wraps two {@link Converter} converters and an intermediate type.
 * <br>
 * The source value and the intermediate type are passed to the first {@link Converter}. If that is successful,
 * that result value is then passed to the second {@link Converter} along with the original target type. That result
 * is then returned.
 * <pre>
 * String -> JSON -> SpreadsheetCell
 *
 * StringToJsonNode -> JsonNodeToUnmarshall
 * <pre>
 */
final class ChainConverter<C extends ConverterContext> implements Converter<C> {

    static <C extends ConverterContext> ChainConverter<C> with(final Converter<C> first,
                                                               final Class<?> intermediateType,
                                                               final Converter<C> second) {
        Objects.requireNonNull(first, "first");
        Objects.requireNonNull(intermediateType, "intermediateType");
        Objects.requireNonNull(second, "second");

        return new ChainConverter<>(
                first,
                intermediateType,
                second
        );
    }

    private ChainConverter(final Converter<C> first,
                           final Class<?> intermediateType,
                           final Converter<C> second) {
        super();
        this.first = first;
        this.intermediateType = intermediateType;
        this.second = second;
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return this.convert(
                value,
                type,
                context
        ).isLeft();
    }

    @Override
    public <T> Either<T, String> convert(final Object value,
                                         final Class<T> type,
                                         final C context) {
        Either<T, String> result;

        final Either<?, String> intermediateResult = this.first.convert(
                value,
                this.intermediateType,
                context
        );
        if (intermediateResult.isLeft()) {
            result = this.second.convert(
                    intermediateResult.leftValue(),
                    type,
                    context
            );

            if (result.isRight()) {
                result = this.failConversion(
                        value,
                        type
                );
            }
        } else {
            result = this.failConversion(
                    value,
                    type
            );
        }

        return result;
    }

    private final Converter<C> first;

    private final Class<?> intermediateType;

    private final Converter<C> second;

    // Object...........................................................................................................

    @Override
    public int hashCode() {
        return Objects.hash(
                this.first,
                this.intermediateType,
                this.second
        );
    }

    @Override
    public boolean equals(final Object other) {
        return this == other ||
                other instanceof ChainConverter && this.equals0(Cast.to(other));
    }

    private boolean equals0(final ChainConverter<?> other) {
        return this.first.equals(other.first) &&
                this.intermediateType.equals(other.intermediateType) &&
                this.second.equals(other.second);
    }

    @Override
    public String toString() {
        if (null == this.toString) {
            final String first = this.first.toString();
            final String second = this.second.toString();

            String toString = null;

            final int firstToIndex = first.lastIndexOf(" to ");
            if (-1 != firstToIndex) {
                final int secondToIndex = second.indexOf(" to ");
                if (-1 != secondToIndex) {
                    final String firstTo = first.substring(firstToIndex + 4);
                    final String secondTO = second.substring(0, secondToIndex);

                    if (firstTo.equals(secondTO)) {
                        toString = first + second.substring(secondToIndex);
                    }
                }
            }

            if (null == toString) {
                toString = first + " to " + second;
            }
            this.toString = toString;
        }

        return this.toString;
    }

    private transient String toString;
}
