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
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.datetime.HasDateTimeSymbols;
import walkingkooka.datetime.HasOptionalDateTimeSymbols;

/**
 * A Converter that converts any {@link HasDateTimeSymbols} or {@link HasOptionalDateTimeSymbols} into a {@link DateTimeSymbols}.
 */
final class ConverterToDateTimeSymbols<C extends ConverterContext> implements TryingShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterToDateTimeSymbols<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterToDateTimeSymbols<?> INSTANCE = new ConverterToDateTimeSymbols<>();

    /**
     * Private to stop sub classing.
     */
    private ConverterToDateTimeSymbols() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return (null == value ||
            value instanceof HasDateTimeSymbols ||
            value instanceof HasOptionalDateTimeSymbols) &&
            DateTimeSymbols.class == type;
    }

    @Override
    public Object tryConvertOrFail(final Object value,
                                   final Class<?> type,
                                   final C context) {
        return value instanceof HasDateTimeSymbols ?
            ((HasDateTimeSymbols) value).dateTimeSymbols() :
            value instanceof HasOptionalDateTimeSymbols ?
                ((HasOptionalDateTimeSymbols) value).dateTimeSymbols()
                    .orElse(null) :
                null;
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return "to" + DateTimeSymbols.class.getSimpleName();
    }
}
