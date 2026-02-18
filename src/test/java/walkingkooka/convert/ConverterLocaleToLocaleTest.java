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
import walkingkooka.datetime.DateTimeContexts;
import walkingkooka.locale.LocaleContexts;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;
import walkingkooka.util.HasLocale;
import walkingkooka.util.HasOptionalLocale;

import java.util.Locale;
import java.util.Optional;

public final class ConverterLocaleToLocaleTest extends ConverterLocaleToTestCase<ConverterLocaleToLocale<ConverterContext>, Locale> {

    private final static Locale LOCALE = Locale.ENGLISH;

    @Test
    public void testConvertLocale() {
        this.convertAndCheck(LOCALE);
    }

    @Test
    public void testConvertHasLocale() {
        this.convertAndCheck(
            new HasLocale() {
                @Override
                public Locale locale() {
                    return LOCALE;
                }
            },
            LOCALE
        );
    }

    @Test
    public void testConvertHasOptionalLocale() {
        this.convertAndCheck(
            new HasOptionalLocale() {
                @Override
                public Optional<Locale> locale() {
                    return Optional.of(LOCALE);
                }
            },
            LOCALE
        );
    }

    @Test
    public void testConvertHasOptionalLocaleMissing() {
        this.convertAndCheck(
            new HasOptionalLocale() {
                @Override
                public Optional<Locale> locale() {
                    return Optional.empty();
                }
            },
            Locale.class,
            null
        );
    }

    @Test
    public void testConvertStringToLocale() {
        this.convertAndCheck(
            LOCALE.toLanguageTag(),
            LOCALE
        );
    }

    @Override
    public ConverterLocaleToLocale<ConverterContext> createConverter() {
        return ConverterLocaleToLocale.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.basic(
            (l) -> {
                throw new UnsupportedOperationException();
            }, // canCurrencyForLocale
            false, // canNumbersHaveGroupSeparator
            Converters.EXCEL_1904_DATE_SYSTEM_OFFSET,
            Indentation.SPACES2,
            LineEnding.NL,
            ',', // valueSeparator
            Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString(),
            DateTimeContexts.fake(),
            DecimalNumberContexts.fake(),
            LocaleContexts.jre(
                Locale.forLanguageTag("en-AU")
            )
        );
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            ConverterLocaleToLocale.instance(),
            Locale.class.getSimpleName()
        );
    }

    // class............................................................................................................


    @Override
    public Class<ConverterLocaleToLocale<ConverterContext>> type() {
        return Cast.to(ConverterLocaleToLocale.class);
    }
}
