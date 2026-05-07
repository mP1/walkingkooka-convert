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
import walkingkooka.HasValue;

/**
 * A {@link Converter} that unwraps {@link HasValue} and then converts that to the requested target type.
 */
final class ConverterHasValueTo<C extends ConverterContext> implements ShortCircuitingConverter<C> {

    /**
     * Instance
     */
    static <C extends ConverterContext> ConverterHasValueTo<C> instance() {
        return INSTANCE;
    }

    /**
     * Singleton
     */
    private final static ConverterHasValueTo INSTANCE = new ConverterHasValueTo<>();

    private ConverterHasValueTo() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return null == value ||
            value instanceof HasValue;
    }

    @Override
    public <T> Either<T, String> doConvert(final Object value,
                                           final Class<T> type,
                                           final C context) {
        return context.convert(
            null == value ?
                null :
                ((HasValue<?>) value)
                    .value(),
            type
        );
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return HasValue.class.getSimpleName() + " to";
    }
}
