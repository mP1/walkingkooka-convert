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
import walkingkooka.math.Maths;

import java.time.LocalDateTime;

/**
 * Converts a {@link LocalDateTime} into a {@link Number}.
 */
final class ConverterTemporalLocalDateTimeToNumber<C extends ConverterContext> extends ConverterTemporalLocalDateTime<Number, C> {

    /**
     * Creates a new instance with the given date offset.
     * A value of zero = 1/1/1970.
     */
    static <C extends ConverterContext> ConverterTemporalLocalDateTimeToNumber<C> instance() {
        return Cast.to(INSTANCE);
    }

    private final static ConverterTemporalLocalDateTimeToNumber<ConverterContext> INSTANCE = new ConverterTemporalLocalDateTimeToNumber<>();

    /**
     * Private ctor use factory
     */
    private ConverterTemporalLocalDateTimeToNumber() {
        super();
    }

    @Override
    boolean canConvertType(final Class<?> type) {
        return Number.class == type || Maths.isNumberClass(type);
    }

    @Override
    <T> Either<T, String> convertDateTimeTo(final LocalDateTime dateTime,
                                            final Class<T> type,
                                            final ConverterContext context) {
        return this.convertToNumber(
            dateTime.toLocalDate().toEpochDay() +
                ((float) dateTime.toLocalTime().toNanoOfDay() / Converters.NANOS_PER_DAY)
                - context.dateOffset(),
            type,
            context
        );
    }

    @Override
    Class<Number> targetType() {
        return Number.class;
    }
}