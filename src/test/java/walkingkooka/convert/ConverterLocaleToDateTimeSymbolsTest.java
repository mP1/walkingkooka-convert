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
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.datetime.HasDateTimeSymbols;
import walkingkooka.datetime.HasOptionalDateTimeSymbols;
import walkingkooka.util.HasLocale;
import walkingkooka.util.HasOptionalLocale;

import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.Optional;

public final class ConverterLocaleToDateTimeSymbolsTest extends ConverterLocaleToTestCase<ConverterLocaleToDateTimeSymbols<ConverterContext>, DateTimeSymbols> {

    private final static Locale LOCALE = Locale.ENGLISH;

    private final static DateTimeSymbols DATE_TIME_SYMBOLS = DateTimeSymbols.fromDateFormatSymbols(
        new DateFormatSymbols(LOCALE)
    );

    @Test
    public void testConvertDateTimeSymbols() {
        this.convertAndCheck(DATE_TIME_SYMBOLS);
    }

    @Test
    public void testConvertHasLocaleToDateTimeSymbols() {
        this.convertAndCheck(
            new HasLocale() {

                @Override
                public Locale locale() {
                    return LOCALE;
                }
            },
            DateTimeSymbols.class,
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
                public Optional<DateTimeSymbols> dateTimeSymbolsForLocale(final Locale locale) {
                    return Optional.of(DATE_TIME_SYMBOLS);
                }
            },
            DATE_TIME_SYMBOLS
        );
    }

    @Test
    public void testConvertHasOptionalLocaleToDateTimeSymbols() {
        this.convertAndCheck(
            new HasOptionalLocale() {

                @Override
                public Optional<Locale> locale() {
                    return Optional.of(LOCALE);
                }
            },
            DateTimeSymbols.class,
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
                public Optional<DateTimeSymbols> dateTimeSymbolsForLocale(final Locale locale) {
                    return Optional.of(DATE_TIME_SYMBOLS);
                }
            },
            DATE_TIME_SYMBOLS
        );
    }

    @Test
    public void testConvertHasOptionalLocaleToDateTimeSymbolsWhenEmptyHasOptionalLocale() {
        this.convertAndCheck(
            new HasOptionalLocale() {

                @Override
                public Optional<Locale> locale() {
                    return Optional.empty();
                }
            },
            DateTimeSymbols.class,
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
            },
            null
        );
    }

    @Test
    public void testConvertLocaleToDateTimeSymbols() {
        this.convertAndCheck(
            LOCALE,
            DateTimeSymbols.class,
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
                public Optional<DateTimeSymbols> dateTimeSymbolsForLocale(final Locale locale) {
                    return Optional.of(DATE_TIME_SYMBOLS);
                }
            },
            DATE_TIME_SYMBOLS
        );
    }

    @Test
    public void testConvertHasDateTimeSymbols() {
        this.convertAndCheck(
            new HasDateTimeSymbols() {
                @Override
                public DateTimeSymbols dateTimeSymbols() {
                    return DATE_TIME_SYMBOLS;
                }
            },
            DATE_TIME_SYMBOLS
        );
    }

    @Test
    public void testConvertHasOptionalDateTimeSymbols() {
        this.convertAndCheck(
            new HasOptionalDateTimeSymbols() {
                @Override
                public Optional<DateTimeSymbols> dateTimeSymbols() {
                    return Optional.of(DATE_TIME_SYMBOLS);
                }
            },
            DATE_TIME_SYMBOLS
        );
    }

    @Test
    public void testConvertHasOptionalDateTimeSymbolsWhenEmpty() {
        this.convertAndCheck(
            new HasOptionalDateTimeSymbols() {
                @Override
                public Optional<DateTimeSymbols> dateTimeSymbols() {
                    return Optional.empty();
                }
            },
            DateTimeSymbols.class,
            null // expected null
        );
    }

    @Test
    public void testConvertStringToDateSymbolsWithLocaleLanguageTag() {
        final Locale locale = Locale.ENGLISH;

        this.convertAndCheck(
            locale.toLanguageTag(),
            DateTimeSymbols.class,
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
                public Optional<DateTimeSymbols> dateTimeSymbolsForLocale(final Locale locale) {
                    return Optional.of(DATE_TIME_SYMBOLS);
                }
            },
            DateTimeSymbols.fromDateFormatSymbols(
                new DateFormatSymbols(locale)
            )
        );
    }

    @Override
    public ConverterLocaleToDateTimeSymbols<ConverterContext> createConverter() {
        return ConverterLocaleToDateTimeSymbols.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            ConverterLocaleToDateTimeSymbols.instance(),
            DateTimeSymbols.class.getSimpleName()
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterLocaleToDateTimeSymbols<ConverterContext>> type() {
        return Cast.to(ConverterLocaleToDateTimeSymbols.class);
    }
}
