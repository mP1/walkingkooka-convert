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
 * Handles converting {@link walkingkooka.Binary} to {@link String}.
 */
final class ConverterBinaryToString<C extends ConverterContext> implements TryingShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterBinaryToString<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterBinaryToString<?> INSTANCE = new ConverterBinaryToString<>();

    /**
     * Private ctor use singleton.
     */
    private ConverterBinaryToString() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return value instanceof HasBinary &&
            String.class == type;
    }

    @Override
    public Object tryConvertOrFail(final Object value,
                                   final Class<?> type,
                                   final C context) {
        final Binary binary = ((HasBinary)value).binary();
        return binary.text(
            context.charset()
        );
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return Binary.class.getSimpleName() + " to " + String.class.getSimpleName();
    }
}
