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

import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.util.HasLocale;
import walkingkooka.util.HasOptionalLocale;

import java.util.Locale;

/**
 * A templated {@link Converter} that handles {@link Locale} and other value types dispatching to
 * one of two abstract methods.
 */
abstract class ConverterLocaleTo<T, C extends ConverterContext> implements TryingShortCircuitingConverter<C> {

    ConverterLocaleTo() {
        super();
    }

    @Override
    public final boolean canConvert(final Object value,
                                    final Class<?> type,
                                    final C context) {
        return (this.targetType() == type &&
            (
                value instanceof Locale ||
                    value instanceof HasLocale ||
                    value instanceof HasOptionalLocale ||
                    this.canConvertNotString(
                        value,
                        context
                    ) ||
                    context.canConvert(value, String.class) // value might be String holding a Locale.
            )
        );
    }

    /**
     * The target type, eg {@link DateTimeSymbols}.
     */
    abstract Class<T> targetType();

    abstract boolean canConvertNotString(final Object value,
                                         final C context);

    @Override
    public final T tryConvertOrFail(final Object value,
                                    final Class<?> type,
                                    final C context) {
        return value instanceof Locale ?
            this.tryConvertLocale(
                (Locale) value,
                context
            ) :
            this.tryConvertNonLocale(
                value,
                context
            );
    }

    abstract T tryConvertLocale(final Locale locale,
                                final C context);

    abstract T tryConvertNonLocale(final Object value,
                                   final C context);

    // Object...........................................................................................................

    @Override
    public final String toString() {
        return this.targetType().getSimpleName();
    }
}
