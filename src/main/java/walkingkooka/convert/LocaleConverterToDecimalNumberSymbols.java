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

import walkingkooka.math.DecimalNumberSymbols;
import walkingkooka.math.HasDecimalNumberSymbols;
import walkingkooka.math.HasOptionalDecimalNumberSymbols;

import java.util.Locale;

/**
 * A {@link Converter} that supports several different source types to return a {@link DecimalNumberSymbols}.
 */
final class LocaleConverterToDecimalNumberSymbols<C extends ConverterContext> extends LocaleConverterTo<DecimalNumberSymbols, C> {

    /**
     * Type safe getter.
     */
    static <C extends ConverterContext> LocaleConverterToDecimalNumberSymbols<C> instance() {
        return INSTANCE;
    }

    /**
     * Singleton
     */
    private final static LocaleConverterToDecimalNumberSymbols INSTANCE = new LocaleConverterToDecimalNumberSymbols();

    private LocaleConverterToDecimalNumberSymbols() {
        super();
    }

    // LocaleConverterTo..................................................................................................

    @Override
    Class<DecimalNumberSymbols> targetType() {
        return DecimalNumberSymbols.class;
    }

    @Override
    boolean canConvertNotString(final Object value,
                                final C context) {
        return (value instanceof DecimalNumberSymbols ||
            value instanceof HasDecimalNumberSymbols ||
            value instanceof HasOptionalDecimalNumberSymbols);
    }

    @Override
    DecimalNumberSymbols tryConvertLocale(final Locale locale,
                                          final C context) {
        return context.decimalNumberSymbolsForLocale(locale)
            .orElse(null);
    }

    @Override
    DecimalNumberSymbols tryConvertNonLocale(final Object value,
                                             final C context) {
        DecimalNumberSymbols result;

        if (value instanceof DecimalNumberSymbols) {
            result = (DecimalNumberSymbols) value;
        } else {
            if (value instanceof HasDecimalNumberSymbols) {
                result = ((HasDecimalNumberSymbols) value).decimalNumberSymbols();
            } else {
                if (value instanceof HasOptionalDecimalNumberSymbols) {
                    result = ((HasOptionalDecimalNumberSymbols) value).decimalNumberSymbols()
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
