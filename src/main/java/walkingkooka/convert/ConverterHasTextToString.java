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
import walkingkooka.text.HasText;

/**
 * A Converter that converts any {@link HasText} into a {@link String}.
 */
final class ConverterHasTextToString<C extends ConverterContext> extends Converter2<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterHasTextToString<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterHasTextToString<?> INSTANCE = new ConverterHasTextToString<>();


    /**
     * Private to stop sub classing.
     */
    private ConverterHasTextToString() {
        super();
    }

    @Override
    boolean canConvertNonNull(final Object value,
                              final Class<?> type,
                              final C context) {
        return value instanceof HasText && String.class == type;
    }

    @Override
    boolean canConvertType(final Class<?> type) {
        return type == String.class;
    }

    @Override //
    <T> Either<T, String> convertNonNull(final Object value,
                                         final Class<T> type,
                                         final ConverterContext context) {
        final HasText hasText = (HasText) value;

        return this.successfulConversion(
                hasText.text(),
                type
        );
    }

    @Override
    public String toString() {
        return "HasText to String";
    }
}
