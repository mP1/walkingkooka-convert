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

/**
 * A {@link Converter} which only accepts a single source type and a single target type, with an offset which is
 * added to the date component.
 */
abstract class ConverterTemporal<S, D, C extends ConverterContext> implements ShortCircuitingConverter<C> {

    ConverterTemporal() {
        super();
    }

    @Override
    public final boolean canConvert(final Object value,
                                    final Class<?> type,
                                    final C context) {
        return (
            null == value ||
                this.canConvertNonNull(
                    value,
                    type,
                    context
                )
        ) && this.canConvertType(type);
    }

    abstract boolean canConvertNonNull(final Object value,
                                       final Class<?> type,
                                       final C context);

    abstract boolean canConvertType(final Class<?> type);

    @Override
    public <T> Either<T, String> doConvert(final Object value,
                                           final Class<T> type,
                                           final C context) {
        return null == value ?
            this.successfulConversion(
                value,
                type
            ) : this.doConvertNonNull(
            (S) value,
            type,
            context
        );
    }

    abstract <T> Either<T, String> doConvertNonNull(final S value,
                                                    final Class<T> type,
                                                    final ConverterContext context);

    @Override
    public final String toString() {
        return this.sourceType().getSimpleName() + " to " + this.targetType().getSimpleName();
    }

    abstract Class<S> sourceType();

    abstract Class<D> targetType();

    /**
     * Helper that performs the last step by converting a {@link Number} to another {@link Number sub class}.
     */
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
