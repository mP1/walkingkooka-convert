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
import java.time.LocalTime;

/**
 * A {@link Converter} that handles converting any {@link Number} to a {@link LocalTime}.
 * The value is the number of seconds in a day.
 */
final class ConverterNumberToLocalTime<C extends ConverterContext> extends ConverterNumber<LocalTime, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterNumberToLocalTime<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterNumberToLocalTime<?> INSTANCE = new ConverterNumberToLocalTime<>();

    /**
     * Private ctor
     */
    private ConverterNumberToLocalTime() {
        super();
    }

    @Override
    Either<LocalTime, String> bigDecimal(final BigDecimal value,
                                         final ConverterContext context) {
        return this.localTime(
            value.doubleValue()
        );
    }

    @Override
    Either<LocalTime, String> bigInteger(final BigInteger value,
                                         final ConverterContext context) {
        return this.localTime(value.doubleValue());
    }

    @SuppressWarnings("UnnecessaryUnboxing")
    @Override
    Either<LocalTime, String> doubleValue(final Double value,
                                          final ConverterContext context) {
        return this.localTime(value);
    }

    @Override
    Either<LocalTime, String> longValue(final Long value,
                                        final ConverterContext context) {
        return this.localTime(value);
    }

    private Either<LocalTime, String> localTime(final double value) {
        return
            (Double.isNaN(value) || value < 0 || value >= 1) ?
                this.failConversion(
                    value,
                    LocalTime.class
                ) :
                this.successfulConversion(
                    LocalTime.ofNanoOfDay(
                        (long)(value * Converters.NANOS_PER_DAY)
                    ),
                    LocalTime.class
                );
    }

    @Override
    Class<LocalTime> targetType() {
        return LocalTime.class;
    }
}
