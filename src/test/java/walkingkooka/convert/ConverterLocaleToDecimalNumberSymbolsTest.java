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

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;
import walkingkooka.Either;
import walkingkooka.collect.list.Lists;
import walkingkooka.math.DecimalNumberSymbols;
import walkingkooka.math.HasDecimalNumberSymbols;
import walkingkooka.math.HasOptionalDecimalNumberSymbols;
import walkingkooka.util.HasLocale;
import walkingkooka.util.HasOptionalLocale;

import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Optional;

public final class ConverterLocaleToDecimalNumberSymbolsTest extends ConverterLocaleToTestCase<ConverterLocaleToDecimalNumberSymbols<ConverterContext>, DecimalNumberSymbols> {

    private final static Locale LOCALE = Locale.ENGLISH;

    private final static DecimalNumberSymbols DECIMAL_NUMBER_SYMBOLS = DecimalNumberSymbols.fromDecimalFormatSymbols(
        '+',
        new DecimalFormatSymbols(LOCALE)
    );

    @Test
    public void testConvertDecimalNumberSymbols() {
        this.convertAndCheck(DECIMAL_NUMBER_SYMBOLS);
    }

    @Test
    public void testConvertHasDecimalNumberSymbols() {
        this.convertAndCheck(
            new HasDecimalNumberSymbols() {
                @Override
                public DecimalNumberSymbols decimalNumberSymbols() {
                    return DECIMAL_NUMBER_SYMBOLS;
                }
            },
            DECIMAL_NUMBER_SYMBOLS
        );
    }

    @Test
    public void testConvertHasOptionalDecimalNumberSymbols() {
        this.convertAndCheck(
            new HasOptionalDecimalNumberSymbols() {
                @Override
                public Optional<DecimalNumberSymbols> decimalNumberSymbols() {
                    return Optional.of(DECIMAL_NUMBER_SYMBOLS);
                }
            },
            DECIMAL_NUMBER_SYMBOLS
        );
    }

    @Test
    public void testConvertHasOptionalDecimalNumberSymbolsWhenEmpty() {
        this.convertAndCheck(
            new HasOptionalDecimalNumberSymbols() {
                @Override
                public Optional<DecimalNumberSymbols> decimalNumberSymbols() {
                    return Optional.empty();
                }
            },
            DecimalNumberSymbols.class,
            null // expected null
        );
    }

    @Test
    public void testConvertHasLocaleToDecimalNumberSymbols() {
        this.convertAndCheck(
            new HasLocale() {

                @Override
                public Locale locale() {
                    return LOCALE;
                }
            },
            DecimalNumberSymbols.class,
            new FakeConverterContext() {
                @Override
                public boolean canConvert(final Object value,
                                          final Class<?> type) {
                    return this.converter.canConvert(
                        value,
                        type,
                        this
                    );
                }

                @Override
                public <T> Either<T, String> convert(final Object value,
                                                     final Class<T> target) {
                    return this.converter.convert(
                        value,
                        target,
                        this
                    );
                }

                private final Converter<ConverterContext> converter = Converters.toLocale();

                @Override
                public Optional<DecimalNumberSymbols> decimalNumberSymbolsForLocale(final Locale locale) {
                    return Optional.of(DECIMAL_NUMBER_SYMBOLS);
                }
            },
            DECIMAL_NUMBER_SYMBOLS
        );
    }

    @Test
    public void testConvertHasOptionalLocaleToDecimalNumberSymbols() {
        this.convertAndCheck(
            new HasOptionalLocale() {

                @Override
                public Optional<Locale> locale() {
                    return Optional.of(LOCALE);
                }
            },
            DecimalNumberSymbols.class,
            new FakeConverterContext() {
                @Override
                public boolean canConvert(final Object value,
                                          final Class<?> type) {
                    return this.converter.canConvert(
                        value,
                        type,
                        this
                    );
                }

                @Override
                public <T> Either<T, String> convert(final Object value,
                                                     final Class<T> target) {
                    return this.converter.convert(
                        value,
                        target,
                        this
                    );
                }

                private final Converter<ConverterContext> converter = Converters.toLocale();

                @Override
                public Optional<DecimalNumberSymbols> decimalNumberSymbolsForLocale(final Locale locale) {
                    return Optional.of(DECIMAL_NUMBER_SYMBOLS);
                }
            },
            DECIMAL_NUMBER_SYMBOLS
        );
    }

    @Test
    public void testConvertHasOptionalLocaleToDecimalNumberSymbolsWhenEmpty() {
        this.convertAndCheck(
            new HasOptionalLocale() {

                @Override
                public Optional<Locale> locale() {
                    return Optional.empty();
                }
            },
            DecimalNumberSymbols.class,
            new FakeConverterContext() {
                @Override
                public boolean canConvert(final Object value,
                                          final Class<?> type) {
                    return this.converter.canConvert(
                        value,
                        type,
                        this
                    );
                }

                @Override
                public <T> Either<T, String> convert(final Object value,
                                                     final Class<T> target) {
                    return this.converter.convert(
                        value,
                        target,
                        this
                    );
                }

                private final Converter<ConverterContext> converter = Converters.toLocale();

                @Override
                public Optional<DecimalNumberSymbols> decimalNumberSymbolsForLocale(final Locale locale) {
                    return Optional.of(DECIMAL_NUMBER_SYMBOLS);
                }
            },
            null
        );
    }

    @Test
    public void testConvertLocaleToDecimalNumberSymbols() {
        this.convertAndCheck(
            LOCALE,
            DecimalNumberSymbols.class,
            new FakeConverterContext() {
                @Override
                public boolean canConvert(final Object value,
                                          final Class<?> type) {
                    return this.converter.canConvert(
                        value,
                        type,
                        this
                    );
                }

                @Override
                public <T> Either<T, String> convert(final Object value,
                                                     final Class<T> target) {
                    return this.converter.convert(
                        value,
                        target,
                        this
                    );
                }

                private final Converter<ConverterContext> converter = Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString();

                @Override
                public Optional<DecimalNumberSymbols> decimalNumberSymbolsForLocale(final Locale locale) {
                    return Optional.of(DECIMAL_NUMBER_SYMBOLS);
                }
            },
            DECIMAL_NUMBER_SYMBOLS
        );
    }

    @Test
    public void testConvertStringToDateSymbolsWithLocaleLanguageTag() {
        this.convertAndCheck(
            LOCALE.toLanguageTag(),
            DecimalNumberSymbols.class,
            new FakeConverterContext() {

                @Override
                public boolean canConvert(final Object value,
                                          final Class<?> type) {
                    return this.converter.canConvert(
                        value,
                        type,
                        this
                    );
                }

                @Override
                public <T> Either<T, String> convert(final Object value,
                                                     final Class<T> target) {
                    return this.converter.convert(
                        value,
                        target,
                        this
                    );
                }

                private final Converter<ConverterContext> converter = Converters.collection(
                    Lists.of(
                        Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString(),
                        Converters.toLocale()
                    )
                );

                @Override
                public Optional<DecimalNumberSymbols> decimalNumberSymbolsForLocale(final Locale locale) {
                    return Optional.of(DECIMAL_NUMBER_SYMBOLS);
                }

                @Override
                public Optional<Locale> localeForLanguageTag(final String languageTag) {
                    return Optional.of(
                        Locale.forLanguageTag(languageTag)
                    );
                }
            },
            DECIMAL_NUMBER_SYMBOLS
        );
    }

    @Override
    public ConverterLocaleToDecimalNumberSymbols<ConverterContext> createConverter() {
        return ConverterLocaleToDecimalNumberSymbols.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    // toString.........................................................................................................

    @Test
    @Override
    public void testToString() {
        this.toStringAndCheck(
            ConverterLocaleToDecimalNumberSymbols.instance(),
            DecimalNumberSymbols.class.getSimpleName()
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterLocaleToDecimalNumberSymbols<ConverterContext>> type() {
        return Cast.to(ConverterLocaleToDecimalNumberSymbols.class);
    }
}
