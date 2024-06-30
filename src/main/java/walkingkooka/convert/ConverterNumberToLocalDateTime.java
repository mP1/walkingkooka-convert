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
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * A {@link Converter} that handles converting any {@link Number} to a {@link LocalDateTime}.
 * The integer value becomes the days, and the fraction is a value of a whole day.
 */
final class ConverterNumberToLocalDateTime<C extends ConverterContext> extends ConverterNumber<LocalDateTime, C> {

    /**
     * Types safe getter.
     */
    static <C extends ConverterContext> ConverterNumberToLocalDateTime<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterNumberToLocalDateTime<ConverterContext> INSTANCE = new ConverterNumberToLocalDateTime<>();

    /**
     * Private ctor
     */
    private ConverterNumberToLocalDateTime() {
        super();
    }

    @Override
    Either<LocalDateTime, String> bigDecimal(final BigDecimal value,
                                             final ConverterContext context) {
        final double doubleValue = value.doubleValue();
        return 0 != BigDecimal.valueOf(doubleValue).compareTo(value) ?
                this.failConversion(value, LocalDateTime.class) :
                this.localDateTime(
                        doubleValue,
                        context
                );
    }

    @Override
    Either<LocalDateTime, String> bigInteger(final BigInteger value,
                                             final ConverterContext context) {
        return this.localDateTime(
                value.longValueExact(),
                value,
                context
        );
    }

    @SuppressWarnings("UnnecessaryUnboxing")
    @Override
    Either<LocalDateTime, String> doubleValue(final Double value,
                                              final ConverterContext context) {
        return this.localDateTime(
                value.doubleValue(),
                context
        );
    }

    @Override
    Either<LocalDateTime, String> longValue(final Long value,
                                            final ConverterContext context) {
        return this.localDateTime(
                value,
                value,
                context
        );
    }

    private Either<LocalDateTime, String> localDateTime(final double value,
                                                        final ConverterContext context) {
        return !Double.isFinite(value) ?
                this.failConversion(value, LocalDateTime.class) :
                this.localDateTime0(
                        value,
                        context
                );
    }

    private Either<LocalDateTime, String> localDateTime0(final double value,
                                                         final ConverterContext context) {
        final double days = Math.floor(value);

        return localDateTime(
                (int) days,
                value - days,
                value,
                context
        );
    }

    private Either<LocalDateTime, String> localDateTime(final long longValue,
                                                        final Number value,
                                                        final ConverterContext context) {
        return localDateTime(
                longValue,
                0,
                value,
                context
        );
    }

    private Either<LocalDateTime, String> localDateTime(final long day,
                                                        final double fraction,
                                                        final Object value,
                                                        final ConverterContext context) {
        final double doubleNano = fraction * Converters.NANOS_PER_DAY;
        final long nano = (long) doubleNano;
        return nano != doubleNano ?
                this.failConversion(value, LocalDateTime.class) :
                this.successfulConversion(
                        LocalDateTime.of(
                                LocalDate.ofEpochDay(day - context.dateOffset()),
                                LocalTime.ofNanoOfDay(nano)),
                        LocalDateTime.class
                );
    }

    @Override
    Class<LocalDateTime> targetType() {
        return LocalDateTime.class;
    }
}
