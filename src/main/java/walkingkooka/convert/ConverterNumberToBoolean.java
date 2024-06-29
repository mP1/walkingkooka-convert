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
 * A {@link Converter} that handles converting any {@link Number} to a {@link BigDecimal}, following truth conventions,
 * where zero becomes false and all other values are true.
 */
final class ConverterNumberToBoolean<C extends ConverterContext> extends ConverterNumber<Boolean, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterNumberToBoolean<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterNumberToBoolean<?> INSTANCE = new ConverterNumberToBoolean<>();

    /**
     * Private ctor use singleton
     */
    private ConverterNumberToBoolean() {
        super();
    }

    @Override
    Either<Boolean, String> bigDecimal(final BigDecimal value,
                                       final ConverterContext context) {
        return this.successfulConversion(value.signum() != 0);
    }

    @Override
    Either<Boolean, String> bigInteger(final BigInteger value,
                                       final ConverterContext context) {
        return this.successfulConversion(value.signum() != 0);
    }

    @SuppressWarnings("UnnecessaryUnboxing")
    @Override
    Either<Boolean, String> doubleValue(final Double value,
                                        final ConverterContext context) {
        return this.successfulConversion(
                false == value.isInfinite() && false == value.isNaN() && 0 != value.doubleValue()
        );
    }

    @SuppressWarnings("UnnecessaryUnboxing")
    @Override
    Either<Boolean, String> longValue(final Long value,
                                      final ConverterContext context) {
        return this.successfulConversion(0 != value.longValue());
    }

    private Either<Boolean, String> successfulConversion(final Object value) {
        return this.successfulConversion(value, Boolean.class);
    }

    @Override
    Class<Boolean> targetType() {
        return Boolean.class;
    }
}
