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

import java.util.Locale;

/**
 * A {@link Converter} that converts an Locale as a {@link String} into a {@link Locale}.
 */
final class ConverterTextToLocale<C extends ConverterContext> implements TextToTryingShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTextToLocale<C> instance() {
        return Cast.to(INSTANCE);
    }

    private final static ConverterTextToLocale<?> INSTANCE = new ConverterTextToLocale<>();

    private ConverterTextToLocale() {
        super();
    }

    @Override
    public boolean isTargetType(final Object value,
                                final Class<?> type,
                                final C context) {
        return value != Locale.class &&
            Locale.class == type;
    }

    @Override
    public Object parseText(final String value,
                            final Class<?> type,
                            final C context) {
        return context.localeForLanguageTagOrFail(value);
    }

    @Override
    public String toString() {
        return "Text to " + Locale.class.getSimpleName();
    }
}
