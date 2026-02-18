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

import walkingkooka.Either;
import walkingkooka.currency.CanCurrencyForLocale;
import walkingkooka.datetime.DateTimeContext;
import walkingkooka.datetime.DateTimeContextDelegator;
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.locale.LocaleContext;
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.math.DecimalNumberContextDelegator;
import walkingkooka.math.DecimalNumberSymbols;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;

import java.math.MathContext;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

/**
 * An adaptor for {@link DecimalNumberContext} to {@link ConverterContext}.
 */
final class BasicConverterContext implements ConverterContext,
    DateTimeContextDelegator,
    DecimalNumberContextDelegator {

    /**
     * Creates a new {@link BasicConverterContext}.
     */
    static BasicConverterContext with(final CanCurrencyForLocale canCurrencyForLocale,
                                      final boolean canNumbersHaveGroupSeparator,
                                      final long dateOffset,
                                      final Indentation indentation,
                                      final LineEnding lineEnding,
                                      final char valueSeparator,
                                      final Converter<ConverterContext> converter,
                                      final DateTimeContext dateTimeContext,
                                      final DecimalNumberContext decimalNumberContext,
                                      final LocaleContext localeContext) {
        Objects.requireNonNull(canCurrencyForLocale, "canCurrencyForLocale");
        Objects.requireNonNull(indentation, "indentation");
        Objects.requireNonNull(lineEnding, "lineEnding");
        Objects.requireNonNull(converter, "converter");
        Objects.requireNonNull(dateTimeContext, "dateTimeContext");
        Objects.requireNonNull(decimalNumberContext, "decimalNumberContext");
        Objects.requireNonNull(localeContext, "localeContext");

        return new BasicConverterContext(
            canCurrencyForLocale,
            canNumbersHaveGroupSeparator,
            dateOffset,
            indentation,
            lineEnding,
            valueSeparator,
            converter,
            dateTimeContext,
            decimalNumberContext,
            localeContext
        );
    }

    /**
     * Private ctor use factory
     */
    private BasicConverterContext(final CanCurrencyForLocale canCurrencyForLocale,
                                  final boolean canNumbersHaveGroupSeparator,
                                  final long dateOffset,
                                  final Indentation indentation,
                                  final LineEnding lineEnding,
                                  final char valueSeparator,
                                  final Converter<ConverterContext> converter,
                                  final DateTimeContext dateTimeContext,
                                  final DecimalNumberContext decimalNumberContext,
                                  final LocaleContext localeContext) {
        super();

        this.canCurrencyForLocale = canCurrencyForLocale;

        this.canNumbersHaveGroupSeparator = canNumbersHaveGroupSeparator;

        this.dateOffset = dateOffset;
        this.indentation = indentation;
        this.lineEnding = lineEnding;
        this.valueSeparator = valueSeparator;

        this.converter = converter;
        this.dateTimeContext = dateTimeContext;
        this.decimalNumberContext = decimalNumberContext;
        this.localeContext = localeContext;
    }

    @Override
    public Optional<Currency> currencyForLocale(final Locale locale) {
        return this.canCurrencyForLocale.currencyForLocale(locale);
    }

    private final CanCurrencyForLocale canCurrencyForLocale;

    @Override
    public boolean canNumbersHaveGroupSeparator() {
        return this.canNumbersHaveGroupSeparator;
    }

    private final boolean canNumbersHaveGroupSeparator;

    @Override
    public long dateOffset() {
        return this.dateOffset;
    }

    private final long dateOffset;

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type) {
        return this.converter.canConvert(value, type, this);
    }

    @Override
    public <T> Either<T, String> convert(final Object value,
                                         final Class<T> target) {
        return this.converter.convert(value, target, this);
    }

    private final Converter<ConverterContext> converter;

    @Override
    public Indentation indentation() {
        return this.indentation;
    }

    private final Indentation indentation;

    @Override
    public LineEnding lineEnding() {
        return this.lineEnding;
    }

    private final LineEnding lineEnding;

    @Override
    public char valueSeparator() {
        return this.valueSeparator;
    }

    private final char valueSeparator;

    // DateTimeContext..................................................................................................

    @Override
    public DateTimeContext dateTimeContext() {
        return this.dateTimeContext;
    }

    private final DateTimeContext dateTimeContext;

    // DecimalNumberContext.............................................................................................

    @Override
    public Locale locale() {
        return this.decimalNumberContext.locale();
    }

    @Override
    public MathContext mathContext() {
        return this.decimalNumberContext.mathContext();
    }

    @Override
    public DecimalNumberContext decimalNumberContext() {
        return this.decimalNumberContext;
    }

    private final DecimalNumberContext decimalNumberContext;

    // CanDateTimeSymbolsForLocale......................................................................................

    @Override
    public Optional<DateTimeSymbols> dateTimeSymbolsForLocale(final Locale locale) {
        return this.localeContext.dateTimeSymbolsForLocale(locale);
    }

    // CanDecimalNumberSymbolsForLocale.................................................................................

    @Override
    public Optional<DecimalNumberSymbols> decimalNumberSymbolsForLocale(final Locale locale) {
        return this.localeContext.decimalNumberSymbolsForLocale(locale);
    }

    // CanLocaleForLanguageTag..........................................................................................

    @Override
    public Optional<Locale> localeForLanguageTag(final String languageTag) {
        return this.localeContext.localeForLanguageTag(languageTag);
    }

    private final LocaleContext localeContext;

    @Override
    public String toString() {
        return this.dateTimeContext + " " + this.decimalNumberContext;
    }
}
