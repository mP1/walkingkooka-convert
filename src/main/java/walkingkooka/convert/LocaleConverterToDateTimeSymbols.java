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
import walkingkooka.datetime.HasDateTimeSymbols;
import walkingkooka.datetime.HasOptionalDateTimeSymbols;

import java.util.Locale;

/**
 * A {@link Converter} that supports several different source types to return a {@link DateTimeSymbols}.
 */
final class LocaleConverterToDateTimeSymbols<C extends ConverterContext> extends LocaleConverterTo<DateTimeSymbols, C> {

    /**
     * Type safe getter.
     */
    static <C extends ConverterContext> LocaleConverterToDateTimeSymbols<C> instance() {
        return INSTANCE;
    }

    /**
     * Singleton
     */
    private final static LocaleConverterToDateTimeSymbols INSTANCE = new LocaleConverterToDateTimeSymbols();

    private LocaleConverterToDateTimeSymbols() {
        super();
    }

    // LocaleConverterTo..................................................................................................

    @Override
    Class<DateTimeSymbols> targetType() {
        return DateTimeSymbols.class;
    }

    @Override
    boolean canConvertNotString(final Object value,
                                final C context) {
        return (value instanceof DateTimeSymbols ||
            value instanceof HasDateTimeSymbols ||
            value instanceof HasOptionalDateTimeSymbols);
    }

    @Override
    DateTimeSymbols tryConvertLocale(final Locale locale,
                                     final C context) {
        return context.dateTimeSymbolsForLocale(locale)
            .orElse(null);
    }

    @Override
    DateTimeSymbols tryConvertNonLocale(final Object value,
                                        final C context) {
        DateTimeSymbols result;

        if (value instanceof DateTimeSymbols) {
            result = (DateTimeSymbols) value;
        } else {
            if (value instanceof HasDateTimeSymbols) {
                result = ((HasDateTimeSymbols) value).dateTimeSymbols();
            } else {
                if (value instanceof HasOptionalDateTimeSymbols) {
                    result = ((HasOptionalDateTimeSymbols) value).dateTimeSymbols()
                        .orElse(null);
                } else {
                    final Locale locale = context.convertOrFail(
                        value,
                        Locale.class
                    );

                    if(null != locale) {
                        result = this.tryConvertLocale(
                            locale,
                            context
                        );
                    } else {
                        result = null;
                    }
                }
            }
        }

        return result;
    }
}
