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

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Accepts {@link LocalDateTime} and returns the {@link java.time.LocalDate}
 */
final class ConverterTemporalLocalDateTimeToLocalDate<C extends ConverterContext> extends ConverterTemporalLocalDateTime<LocalDate, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTemporalLocalDateTimeToLocalDate<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterTemporalLocalDateTimeToLocalDate<?> INSTANCE = new ConverterTemporalLocalDateTimeToLocalDate<>();

    /**
     * Private ctor use singleton
     */
    private ConverterTemporalLocalDateTimeToLocalDate() {
        super(0);
    }

    @Override
    boolean canConvertType(final Class<?> type) {
        return LocalDate.class == type;
    }

    @Override
    <T> Either<T, String> convertFromLocalDateTime(final long days,
                                                   final double time,
                                                   final LocalDateTime localDateTime,
                                                   final Class<T> type,
                                                   final ConverterContext context) {
        return this.successfulConversion(
                localDateTime.toLocalDate(),
                type
        );
    }

    @Override
    Class<LocalDate> targetType() {
        return LocalDate.class;
    }
}
