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

import walkingkooka.Binary;
import walkingkooka.Cast;
import walkingkooka.HasBinary;

/**
 * A Converter that converts any {@link HasBinary} into a {@link Binary}.
 */
final class ConverterToBinary<C extends ConverterContext> implements TryingShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterToBinary<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterToBinary<?> INSTANCE = new ConverterToBinary<>();

    /**
     * Private to stop sub classing.
     */
    private ConverterToBinary() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return (null == value ||
            value instanceof HasBinary) &&
            Binary.class == type;
    }

    @Override
    public Object tryConvertOrFail(final Object value,
                                   final Class<?> type,
                                   final C context) {
        return null == value ?
            null :
            ((HasBinary) value).binary();
    }

    @Override
    public String toString() {
        // to Binary
        return "to " + Binary.class.getSimpleName();
    }
}
