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

import java.util.Locale;
import java.util.Optional;

public final class ConverterTextToLocaleTest extends ConverterTestCase2<ConverterTextToLocale<ConverterContext>> {

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
            Locale.forLanguageTag("EN-AU"),
            Locale.class
        );
    }

    @Test
    public void testConvertStringToLocale() {
        final Locale locale = Locale.forLanguageTag("EN-AU");

        this.convertAndCheck(
            locale.toLanguageTag(),
            locale
        );
    }

    @Test
    public void testConvertCharSequenceToLocale() {
        final Locale locale = Locale.forLanguageTag("EN-AU");

        this.convertAndCheck(
            new StringBuilder(locale.toLanguageTag()),
            locale
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
            public Optional<Locale> localeForLanguageTag(final String languageTag) {
                return Optional.of(
                    Locale.forLanguageTag(languageTag)
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
