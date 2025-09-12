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

import java.time.LocalTime;

/**
 * Converts a {@link LocalTime} or null to a given supported type.
 */
abstract class ConverterLocalTime<C extends ConverterContext> implements ShortCircuitingConverter<C> {

    /**
     * Package private to limit sub classing.
     */
    ConverterLocalTime() {
    }

    @Override //
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return (null == value || value instanceof LocalTime) &&
            this.canConvertType(type);
    }

    abstract boolean canConvertType(final Class<?> type);

    @Override
    public <T> Either<T, String> doConvert(final Object value,
                                           final Class<T> type,
                                           final C context) {
        return null == value ?
            this.successfulConversion(
                value,
                type
            ) :
            this.convertTime(
                (LocalTime) value,
                type,
                context
            );
    }

    abstract <T> Either<T, String> convertTime(final LocalTime time,
                                               final Class<T> type,
                                               final ConverterContext context);

    final <N> Either<N, String> convertToNumber(final Number number,
                                                final Class<N> type,
                                                final ConverterContext context) {
        return ConverterNumberToNumber.instance()
            .convert(
                number,
                type,
                context
            );
    }
}
