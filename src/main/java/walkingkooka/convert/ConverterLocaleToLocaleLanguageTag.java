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

import walkingkooka.locale.LocaleLanguageTag;
import walkingkooka.util.HasLocale;
import walkingkooka.util.HasOptionalLocale;

import java.util.Locale;

/**
 * A {@link Converter} that supports converting {@link String text}, {@link HasLocale} or a {@link Locale} to a {@link LocaleLanguageTag}.
 */
final class ConverterLocaleToLocaleLanguageTag<C extends ConverterContext> extends ConverterLocaleTo<LocaleLanguageTag, C> {

    /**
     * Type safe getter.
     */
    static <C extends ConverterContext> ConverterLocaleToLocaleLanguageTag<C> instance() {
        return INSTANCE;
    }

    /**
     * Singleton
     */
    private final static ConverterLocaleToLocaleLanguageTag INSTANCE = new ConverterLocaleToLocaleLanguageTag<>();

    private ConverterLocaleToLocaleLanguageTag() {
        super();
    }

    // ConverterLocaleTo..................................................................................................

    @Override
    Class<LocaleLanguageTag> targetType() {
        return LocaleLanguageTag.class;
    }

    @Override
    boolean canConvertNotString(final Object value,
                                final C context) {
        return value instanceof Locale ||
            value instanceof LocaleLanguageTag ||
            value instanceof HasLocale ||
            value instanceof HasOptionalLocale;
    }

    @Override
    LocaleLanguageTag tryConvertLocale(final Locale locale,
                                       final C context) {
        return null != locale ?
            LocaleLanguageTag.fromLocale(locale) :
            null;
    }

    @Override
    LocaleLanguageTag tryConvertLocaleLanguageTag(final LocaleLanguageTag localeLanguageTag,
                                                  final C context) {
        return localeLanguageTag;
    }

    @Override
    LocaleLanguageTag tryConvertNonLocale(final Object value,
                                          final C context) {
        return null != value ?
            LocaleLanguageTag.parse(
                context.convertOrFail(
                    value,
                    String.class
                )
            ) :
            null;
    }
}
