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
import walkingkooka.math.Maths;

/**
 * Handles converting {@link Number} to {@link Boolean}.
 */
final class BooleanToNumberConverter<C extends ConverterContext> implements Converter<C>,
        TryingShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> BooleanToNumberConverter<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static BooleanToNumberConverter<?> INSTANCE = new BooleanToNumberConverter<>();

    /**
     * Private ctor use singleton.
     */
    private BooleanToNumberConverter() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return Number.class == type || Maths.isNumberClass(type);
    }

    @Override
    public Object tryConvertOrFail(final Object value,
                                   final Class<?> type,
                                   final C context) {
        return null != value ?
                BooleanToNumberConverterNumberTypeVisitor.convert(
                        (Boolean) value,
                        type
                ) :
                null;
    }

    @Override
    public String toString() {
        return "Boolean to Number";
    }
}
