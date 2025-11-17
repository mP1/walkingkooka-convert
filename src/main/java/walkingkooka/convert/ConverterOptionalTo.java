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

import java.util.Optional;

/**
 * A {@link Converter} that unwraps {@link java.util.Optional} values if the element is of the requested type.
 */
final class ConverterOptionalTo<C extends ConverterContext> implements ShortCircuitingConverter<C> {

    /**
     * Instance
     */
    static <C extends ConverterContext> ConverterOptionalTo<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterOptionalTo<?> INSTANCE = new ConverterOptionalTo<>();

    private ConverterOptionalTo() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return null == value ||
            value instanceof Optional;
    }

    @Override
    public <T> Either<T, String> doConvert(final Object value,
                                           final Class<T> type,
                                           final C context) {
        return context.convert(
            null == value ?
                null :
                ((Optional<?>) value)
                    .orElse(null),
            type
        );
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return Optional.class.getSimpleName() + " to";
    }
}
