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

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * A {@link Converter} that handles converting {@link Number} to another {@link Number} type.
 */
abstract class ConverterNumber<T, C extends ConverterContext> implements ShortCircuitingConverter<C> {

    ConverterNumber() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return (null == value || value instanceof Number) &&
            this.targetType() == type;
    }

    abstract Class<T> targetType();

    @Override
    public <T> Either<T, String> doConvert(final Object value,
                                           final Class<T> type,
                                           final C context) {
        return null == value ?
            this.successfulConversion(
                value,
                type
            ) :
            this.convertNonNull(
                value,
                type,
                context
            );
    }

    /**
     * Accepts an assumed {@link Number} and dispatches to one of the sub-classes of {@link Number} which then
     * call one of four abstract methods.
     */
    private <U> Either<U, String> convertNonNull(final Object value,
                                                 final Class<U> type,
                                                 final ConverterContext context) {
        // T and U should be the same...
        Either<U, String> result;
        try {
            result = ConverterNumberToNumberVisitor.convert(
                Cast.to(this),
                (Number) value,
                type,
                context
            );
        } catch (final Exception cause) {
            result = Either.right(cause.getMessage());
        }
        return result;
    }

    abstract Either<T, String> bigDecimal(final BigDecimal value,
                                          final ConverterContext context);

    abstract Either<T, String> bigInteger(final BigInteger value,
                                          final ConverterContext context);

    final Either<T, String> floatValue(final Float value,
                                       final ConverterContext context) {
        return this.doubleValue(
            value.doubleValue(),
            context
        );
    }

    abstract Either<T, String> doubleValue(final Double value,
                                           final ConverterContext context);

    final Either<T, String> number(final Number value,
                                   final ConverterContext context) {
        return this.longValue(
            value.longValue(),
            context
        );
    }

    abstract Either<T, String> longValue(final Long value,
                                         final ConverterContext context);

    // Object...........................................................................................................

    @Override
    public final String toString() {
        return "Number to " + this.targetType().getSimpleName();
    }
}
