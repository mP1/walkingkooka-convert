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
import walkingkooka.currency.CurrencyValue;

/**
 * A Converter that converts any a value to a {@link Number} and then uses the {@link ConverterContext#currencyCode()} to
 * create a {@link walkingkooka.currency.CurrencyValue}.
 */
final class ConverterToCurrencyValue<C extends ConverterContext> implements TryingShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterToCurrencyValue<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterToCurrencyValue<?> INSTANCE = new ConverterToCurrencyValue<>();

    /**
     * Private to stop sub classing.
     */
    private ConverterToCurrencyValue() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return CurrencyValue.class == type;
    }

    @Override
    public Object tryConvertOrFail(final Object value,
                                   final Class<?> type,
                                   final C context) {
        return CurrencyValue.with(
            context.convertOrFail(
                value,
                Number.class
            ),
            context.currencyCode()
        );
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return "to " + CurrencyValue.class.getSimpleName();
    }
}
