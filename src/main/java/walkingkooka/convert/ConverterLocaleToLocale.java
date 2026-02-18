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

/*
 * Copyright 2025 Miroslav Pokorny (github.com/mP1)
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

import walkingkooka.util.HasLocale;
import walkingkooka.util.HasOptionalLocale;

import java.util.Locale;

/**
 * A {@link Converter} that supports converting {@link String text}, {@link HasLocale} or a {@link Locale} to a {@link Locale}.
 */
final class ConverterLocaleToLocale<C extends ConverterContext> extends ConverterLocaleTo<Locale, C> {

    /**
     * Type safe getter.
     */
    static <C extends ConverterContext> ConverterLocaleToLocale<C> instance() {
        return INSTANCE;
    }

    /**
     * Singleton
     */
    private final static ConverterLocaleToLocale INSTANCE = new ConverterLocaleToLocale();

    private ConverterLocaleToLocale() {
        super();
    }

    // ConverterLocaleTo..................................................................................................

    @Override
    Class<Locale> targetType() {
        return Locale.class;
    }

    @Override
    boolean canConvertNotString(final Object value,
                                final C context) {
        return value instanceof Locale ||
            value instanceof HasLocale ||
            value instanceof HasOptionalLocale;
    }

    @Override
    Locale tryConvertLocale(final Locale locale,
                            final C context) {
        return locale;
    }

    @Override
    Locale tryConvertNonLocale(final Object value,
                               final C context) {
        Locale locale;

        if (value instanceof Locale) {
            locale = (Locale) value;
        } else {
            if (value instanceof HasLocale) {
                locale = ((HasLocale) value)
                    .locale();
            } else {
                if (value instanceof HasOptionalLocale) {
                    locale = ((HasOptionalLocale) value)
                        .locale()
                        .orElse(null);
                } else {
                    locale = context.localeForLanguageTagOrFail(
                        context.convertOrFail(
                            value,
                            String.class
                        )
                    );
                }
            }
        }

        return locale;
    }
}
