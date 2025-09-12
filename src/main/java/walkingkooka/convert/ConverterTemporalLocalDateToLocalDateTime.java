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
import java.time.LocalTime;

/**
 * A {@link Converter} that converts {@link LocalDate} into {@link LocalDateTime}.
 */
final class ConverterTemporalLocalDateToLocalDateTime<C extends ConverterContext> extends ConverterTemporalLocalDate<LocalDateTime, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTemporalLocalDateToLocalDateTime<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterTemporalLocalDateToLocalDateTime<?> INSTANCE = new ConverterTemporalLocalDateToLocalDateTime<>();

    private ConverterTemporalLocalDateToLocalDateTime() {
        super();
    }

    @Override
    boolean canConvertType(final Class<?> type) {
        return LocalDateTime.class == type;
    }

    @Override
    <T> Either<T, String> doConvertNonNull(final LocalDate date,
                                           final Class<T> type,
                                           final ConverterContext context) {
        return this.successfulConversion(
            LocalDateTime.of(date, TIME),
            type
        );
    }

    private final static LocalTime TIME = LocalTime.MIDNIGHT;

    @Override
    Class<LocalDateTime> targetType() {
        return LocalDateTime.class;
    }
}
