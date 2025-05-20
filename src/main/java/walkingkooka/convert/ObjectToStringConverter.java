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

/**
 * A {@link Converter} that invokes {@link Object#toString()} to convert any value into a {@link String}
 */
final class ObjectToStringConverter<C extends ConverterContext> implements TemplatedConverter<C> {

    /**
     * Instance
     */
    static <C extends ConverterContext> ObjectToStringConverter<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ObjectToStringConverter<?> INSTANCE = new ObjectToStringConverter<>();

    private ObjectToStringConverter() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return String.class == type;
    }

    @Override
    public Object tryConvertOrFail(final Object value,
                                   final Class<?> type,
                                   final C context) {
        return String.valueOf(value);
    }

    @Override
    public String toString() {
        return "* to String";
    }
}
