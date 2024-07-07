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
import java.time.LocalDate;

/**
 * A {@link Converter} that handles converting any {@link Number} to a {@link LocalDate}.
 */
final class ConverterNumberToLocalDate<C extends ConverterContext> extends ConverterNumber<LocalDate, C> {

    /**
     * Creates a new instance with the given date offset.
     * A value of zero is 1/1/1970.
     */
    static <C extends ConverterContext> ConverterNumberToLocalDate<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterNumberToLocalDate<ConverterContext> INSTANCE = new ConverterNumberToLocalDate<>();

    /**
     * Private ctor
     */
    private ConverterNumberToLocalDate() {
        super();
    }

    @Override
    Either<LocalDate, String> bigDecimal(final BigDecimal value,
                                         final ConverterContext context) {
        return this.localDate(
                value.longValueExact(),
                context
        );
    }

    @Override
    Either<LocalDate, String> bigInteger(final BigInteger value,
                                         final ConverterContext context) {
        return this.localDate(
                value.longValueExact(),
                context
        );
    }

    @Override
    Either<LocalDate, String> doubleValue(final Double value,
                                          final ConverterContext context) {
        final double doubleValue = value;
        return value != (long) doubleValue ?
                this.failConversion(value, LocalDate.class) :
                this.localDate(
                        (long) doubleValue,
                        context
                );
    }

    @Override
    Either<LocalDate, String> longValue(final Long value,
                                        final ConverterContext context) {
        return this.localDate(
                value,
                context
        );
    }

    private Either<LocalDate, String> localDate(final long value,
                                                final ConverterContext context) {
        return this.successfulConversion(
                LocalDate.ofEpochDay(value + context.dateOffset()),
                LocalDate.class
        );
    }

    @Override
    Class<LocalDate> targetType() {
        return LocalDate.class;
    }
}
