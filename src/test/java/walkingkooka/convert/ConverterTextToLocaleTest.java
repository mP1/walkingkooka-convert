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

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;
import walkingkooka.Either;
import walkingkooka.locale.LocaleLanguageTag;
import walkingkooka.util.HasLocaleTesting;

import java.util.Locale;
import java.util.Optional;

public final class ConverterTextToLocaleTest extends ConverterTextToTestCase<ConverterTextToLocale<ConverterContext>, Locale>
    implements HasLocaleTesting {

    @Test
    public void testConvertEmptyStringToLocaleFails() {
        this.convertFails(
            "",
            Locale.class
        );
    }

    @Test
    public void testConvertUndStringToLocaleFails() {
        this.convertFails(
            "und",
            Locale.class
        );
    }

    @Test
    public void testConvertStringToStringFails() {
        this.convertFails(
            "EN-AU",
            String.class
        );
    }

    @Test
    public void testConvertLocaleToLocaleFails() {
        this.convertFails(
            LOCALE,
            Locale.class
        );
    }

    @Test
    public void testConvertStringToLocale() {
        this.convertAndCheck(
            LOCALE.toLanguageTag(),
            LOCALE
        );
    }

    @Test
    public void testConvertCharSequenceToLocale() {
        this.convertAndCheck(
            new StringBuilder(LOCALE.toLanguageTag()),
            LOCALE
        );
    }

    @Override
    public ConverterTextToLocale<ConverterContext> createConverter() {
        return ConverterTextToLocale.instance();
    }

    @Override
    public FakeConverterContext createContext() {
        return new FakeConverterContext() {
            @Override
            public boolean canConvert(final Object value,
                                      final Class<?> type) {
                return converter.canConvert(
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
            public Optional<Locale> localeForLanguageTag(final LocaleLanguageTag languageTag) {
                return Optional.of(
                    Locale.forLanguageTag(
                        languageTag.value()
                    )
                );
            }
        };
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToLocale<ConverterContext>> type() {
        return Cast.to(ConverterTextToLocale.class);
    }
}
