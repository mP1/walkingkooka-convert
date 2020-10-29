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

import walkingkooka.Either;
import walkingkooka.math.Maths;

import java.time.LocalDate;

/**
 * Converts {@link java.time.LocalDate} to {@link Number}
 */
final class ConverterTemporalLocalDateNumber extends ConverterTemporalLocalDate<Number> {

    /**
     * Factory that creates a new instance with the given date offset.
     * A value of zero = 1/1/1970.
     */
    static ConverterTemporalLocalDateNumber with(final long offset) {
        return new ConverterTemporalLocalDateNumber(offset);
    }

    /**
     * Private ctor use factory
     */
    private ConverterTemporalLocalDateNumber(final long offset) {
        super(offset);
    }

    @Override
    boolean isTargetType(final Class<?> type) {
        return Number.class == type || Maths.isNumberClass(type);
    }

    @Override
    <T> Either<T, String> convert1(final LocalDate date,
                                   final Class<T> type,
                                   final ConverterContext context) {
        return this.convertToNumber(date.toEpochDay() + this.offset,
                type,
                context);
    }

    @Override
    Class<Number> targetType() {
        return Number.class;
    }
}
