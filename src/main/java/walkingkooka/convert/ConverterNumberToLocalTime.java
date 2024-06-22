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
    Either<LocalTime, String> bigDecimal(final BigDecimal value) {
        final double doubleValue = value.doubleValue();
        return 0 != BigDecimal.valueOf(doubleValue).compareTo(value) ?
                this.failConversion(value, LocalTime.class) :
                this.localTime(doubleValue);
    }

    @Override
    Either<LocalTime, String> bigInteger(final BigInteger value) {
        return this.localTime(value.longValueExact());
    }

    @SuppressWarnings("UnnecessaryUnboxing")
    @Override
    Either<LocalTime, String> doubleValue(final Double value) {
        return this.localTime(value.doubleValue());
    }

    @Override
    Either<LocalTime, String> longValue(final Long value) {
        return this.localTime(value);
    }

    private Either<LocalTime, String> localTime(final long value) {
        return this.successfulConversion(
                LocalTime.ofSecondOfDay(value),
                LocalTime.class
        );
    }

    private Either<LocalTime, String> localTime(final double value) {
        final double doubleNanos = value * Converters.NANOS_PER_SECOND;
        final long nanos = (long) doubleNanos;
        return nanos != doubleNanos ?
                this.failConversion(value, LocalTime.class) :
                this.successfulConversion(
                        LocalTime.ofNanoOfDay(nanos),
                        LocalTime.class
                );
    }

    @Override
    Class<LocalTime> targetType() {
        return LocalTime.class;
    }

    @Override
    String toStringPrefix() {
        return "";
    }

    @Override
    String toStringSuffix() {
        return "";
    }
}
