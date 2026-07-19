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
import walkingkooka.currency.CurrencyContexts;
import walkingkooka.datetime.DateTimeContexts;
import walkingkooka.locale.LocaleContexts;
import walkingkooka.locale.LocaleLanguageTag;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;
import walkingkooka.text.TextPrinting;
import walkingkooka.util.HasLocale;
import walkingkooka.util.HasOptionalLocale;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.Optional;

public final class ConverterLocaleToLocaleLanguageTagTest extends ConverterLocaleToTestCase<ConverterLocaleToLocaleLanguageTag<ConverterContext>, LocaleLanguageTag> {

    private final static Locale LOCALE = Locale.forLanguageTag("en-AU");

    private final static LocaleLanguageTag LOCALE_LANGUAGE_TAG = LocaleLanguageTag.fromLocale(LOCALE);

    @Test
    public void testConvertLocaleToLocaleLanguageTag() {
        this.convertAndCheck(
            LOCALE,
            LOCALE_LANGUAGE_TAG
        );
    }

    @Test
    public void testConvertLocaleLanguageTagToLocaleLanguageTag() {
        this.convertAndCheck(
            LOCALE_LANGUAGE_TAG,
            LOCALE_LANGUAGE_TAG
        );
    }

    @Test
    public void testConvertHasLocaleToLocaleLanguageTag() {
        this.convertAndCheck(
            new HasLocale() {
                @Override
                public Locale locale() {
                    return LOCALE;
                }
            },
            LOCALE_LANGUAGE_TAG
        );
    }

    @Test
    public void testConvertHasOptionalLocaleToLocaleLanguageTag() {
        this.convertAndCheck(
            new HasOptionalLocale() {
                @Override
                public Optional<Locale> locale() {
                    return OPTIONAL_LOCALE;
                }
            },
            LOCALE_LANGUAGE_TAG
        );
    }

    @Test
    public void testConvertHasOptionalLocaleEmptyToLocaleLanguageTag() {
        this.convertAndCheck(
            new HasOptionalLocale() {
                @Override
                public Optional<Locale> locale() {
                    return Optional.empty();
                }
            },
            LocaleLanguageTag.class,
            null
        );
    }

    @Test
    public void testConvertStringLocaleLanguageTagToLocaleLanguageTag() {
        this.convertAndCheck(
            LOCALE.toLanguageTag(),
            LOCALE_LANGUAGE_TAG
        );
    }

    @Override
    public ConverterLocaleToLocaleLanguageTag<ConverterContext> createConverter() {
        return ConverterLocaleToLocaleLanguageTag.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.basic(
            false, // canNumbersHaveGroupSeparator
            Converters.EXCEL_1904_DATE_SYSTEM_OFFSET,
            ',', // valueSeparator
            Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString(),
            BinaryNumberConverterFunctions.multiply(),
            TextPrinting.with(
                Indentation.SPACES2,
                LineEnding.NL
            ).setCharset(StandardCharsets.UTF_8),
            CurrencyContexts.fake()
                .setLocaleContext(
                    LocaleContexts.jre(LOCALE)
                ),
            DateTimeContexts.fake(),
            DecimalNumberContexts.fake()
        );
    }

    // toString.........................................................................................................

    @Test
    @Override
    public void testToString() {
        this.toStringAndCheck(
            ConverterLocaleToLocaleLanguageTag.instance(),
            "to " + LocaleLanguageTag.class.getSimpleName()
        );
    }

    // class............................................................................................................


    @Override
    public Class<ConverterLocaleToLocaleLanguageTag<ConverterContext>> type() {
        return Cast.to(ConverterLocaleToLocaleLanguageTag.class);
    }
}
