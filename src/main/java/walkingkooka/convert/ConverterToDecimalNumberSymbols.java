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
import walkingkooka.math.DecimalNumberSymbols;
import walkingkooka.math.HasDecimalNumberSymbols;
import walkingkooka.math.HasOptionalDecimalNumberSymbols;

/**
 * A Converter that converts any {@link HasDecimalNumberSymbols} or {@link HasOptionalDecimalNumberSymbols} into a {@link DecimalNumberSymbols}.
 */
final class ConverterToDecimalNumberSymbols<C extends ConverterContext> implements TryingShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterToDecimalNumberSymbols<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterToDecimalNumberSymbols<?> INSTANCE = new ConverterToDecimalNumberSymbols<>();

    /**
     * Private to stop sub classing.
     */
    private ConverterToDecimalNumberSymbols() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return (null == value ||
            value instanceof HasDecimalNumberSymbols ||
            value instanceof HasOptionalDecimalNumberSymbols) &&
            DecimalNumberSymbols.class == type;
    }

    @Override
    public Object tryConvertOrFail(final Object value,
                                   final Class<?> type,
                                   final C context) {
        return value instanceof HasDecimalNumberSymbols ?
            ((HasDecimalNumberSymbols) value).decimalNumberSymbols() :
            value instanceof HasOptionalDecimalNumberSymbols ?
                ((HasOptionalDecimalNumberSymbols) value).decimalNumberSymbols()
                    .orElse(null) :
                null;
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return "to" + DecimalNumberSymbols.class.getSimpleName();
    }
}
