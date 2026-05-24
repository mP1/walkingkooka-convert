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

import walkingkooka.Context;
import walkingkooka.currency.CanCurrencyForLocale;
import walkingkooka.currency.CurrencyCodeLanguageTagContext;
import walkingkooka.currency.CurrencyExchangeRater;
import walkingkooka.currency.HasCurrencyCode;
import walkingkooka.datetime.DateTimeContext;
import walkingkooka.locale.CanDateTimeSymbolsForLocale;
import walkingkooka.locale.CanDecimalNumberSymbolsForLocale;
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.text.HasIndentation;
import walkingkooka.text.HasLineEnding;

import java.nio.charset.Charset;

/**
 * {@link Context} that accompanies a {@link Converter} and is intended to carry values that may be locale or user aware.
 */
public interface ConverterContext extends ConverterLike,
    CanCurrencyForLocale,
    CanDateTimeSymbolsForLocale,
    CanDecimalNumberSymbolsForLocale,
    CurrencyCodeLanguageTagContext,
    CurrencyExchangeRater,
    DateTimeContext,
    DecimalNumberContext,
    HasCurrencyCode,
    HasIndentation,
    HasLineEnding {

    /**
     * A flag that controls whether text holding a number may or may not include a group-separator.
     * <pre>
     * A primary motivator is the excel numberValue function which supports group-separators within a number.
     * Group separators are a problem because multi value text such as a CSV can also use the same character to separate
     * values.
     * </pre>
     */
    boolean canNumbersHaveGroupSeparator();

    /**
     * The preferred or context default {@link Charset}
     */
    Charset charset();

    /**
     * The offset (relative to 1/1/1970) added when converting a number to a {@link java.time.LocalDate}.
     */
    long dateOffset();

    /**
     * Multiplies two {@link Number} giving the result of the requested {@link Number} type.
     */
    <N extends Number> N multiply(final Number left,
                                  final Number right,
                                  final Class<N> type);

    /**
     * The character that separates one or more values within a {@link String}, such as a CSV.
     */
    char valueSeparator();
}
