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

/**
 * A {@link Converter} that only handles requests to convert to {@link Object}.
 * @param <C>
 */
final class ConverterObject<C extends ConverterContext> extends Converter2<C> {
    /**
     * Instance
     */
    static <C extends ConverterContext> ConverterObject<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterObject<?> INSTANCE = new ConverterObject<>();

    private ConverterObject() {
        super();
    }

    @Override
    boolean canConvertNonNull(final Object value,
                              final Class<?> type,
                              final C context) {
        return this.canConvertType(type);
    }

    @Override
    boolean canConvertType(final Class<?> type) {
        return Object.class == type;
    }

    @Override
    <T> Either<T, String> convertNonNull(final Object value,
                                         final Class<T> type,
                                         final ConverterContext context) {
        return this.successfulConversion(value, type);
    }

    @Override
    public String toString() {
        return "* to " + Object.class.getSimpleName();
    }
}
