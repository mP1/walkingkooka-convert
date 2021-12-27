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

import java.time.LocalTime;

/**
 * Converts a {@link LocalTime} to a given type.
 */
abstract class ConverterLocalTime<C extends ConverterContext> extends Converter2<C> {

    /**
     * Package private to limit sub classing.
     */
    ConverterLocalTime() {
    }

    @Override
    final boolean canConvertNonNull(final Object value,
                              final Class<?> type,
                              final C context) {
        return value instanceof LocalTime;
    }

    @Override
    final <T> Either<T, String> convertNonNull(final Object value,
                                               final Class<T> type,
                                               final ConverterContext context) {
        return this.convert1((LocalTime) value,
                type,
                context);
    }

    private <T> Either<T, String> convert1(final LocalTime value,
                                           final Class<T> type,
                                           final ConverterContext context) {
        return this.convertFromLocalTime(value.toSecondOfDay(),
                value.getNano(),
                value,
                type,
                context);
    }

    abstract <T> Either<T, String> convertFromLocalTime(final long seconds,
                                                        final long nano,
                                                        final LocalTime localTime,
                                                        final Class<T> type,
                                                        final ConverterContext context);
}
