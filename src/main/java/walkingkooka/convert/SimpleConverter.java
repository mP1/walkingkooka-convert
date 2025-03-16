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
 * A {@link Converter} that returns the value if the requested target type is actually the requested target type.
 * This is unfortunate limit of J2CL the Class#isInstance is not support/available.
 */
final class SimpleConverter<C extends ConverterContext> implements Converter<C> {

    /**
     * Type safe getter
     */
    static <C extends ConverterContext> SimpleConverter<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private static final SimpleConverter<?> INSTANCE = new SimpleConverter<>();

    /**
     * Private ctor
     */
    private SimpleConverter() {
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return null == value || type == value.getClass();
    }

    @Override
    public <T> Either<T, String> convert(final Object value,
                                         final Class<T> type,
                                         final C context) {
        return this.canConvert(
                value,
                type,
                context) ?
                this.successfulConversion(value, type) :
                this.failConversion(value, type);
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return "if type";
    }
}
