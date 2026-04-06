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
import walkingkooka.collect.list.Lists;
import walkingkooka.locale.LocaleLanguageTag;
import walkingkooka.locale.LocaleLanguageTagSet;

public final class ConverterTextToCollectionSetLocaleLanguageTagSetTest extends ConverterTextToCollectionSetTestCase<LocaleLanguageTagSet, LocaleLanguageTag, ConverterTextToCollectionSetLocaleLanguageTagSet<ConverterContext>> {

    private final static LocaleLanguageTag LANGUAGE_TAG = LocaleLanguageTag.parse("en-AU");

    private final static LocaleLanguageTag LANGUAGE_TAG2 = LocaleLanguageTag.parse("en-NZ");

    private final static char SEPARATOR = ',';

    @Test
    public void testConvertString() {
        this.convertToCollectionAndCheck(
            LANGUAGE_TAG.toString(),
            LANGUAGE_TAG
        );
    }

    @Test
    public void testConvertStringSurroundingSpacesTrimmed() {
        this.convertToCollectionAndCheck(
            " " + LANGUAGE_TAG2 + " ",
            LANGUAGE_TAG2
        );
    }

    @Test
    public void testConvertCharSequenceString() {
        this.convertToCollectionAndCheck(
            new StringBuilder(LANGUAGE_TAG.toString()),
            LANGUAGE_TAG
        );
    }

    @Test
    public void testConvertStringQuotedString() {
        this.convertToCollectionAndCheck(
            "\"" + LANGUAGE_TAG + "\"",
            LANGUAGE_TAG
        );
    }

    @Test
    public void testConvertStringSeparatorString() {
        this.convertToCollectionAndCheck(
            "" + LANGUAGE_TAG + SEPARATOR + LANGUAGE_TAG2,
            LANGUAGE_TAG,
            LANGUAGE_TAG2
        );
    }

    @Test
    public void testConvertStringSeparatorStringSeparatorString() {
        this.convertToCollectionAndCheck(
            "" + LANGUAGE_TAG + SEPARATOR + LANGUAGE_TAG2 + SEPARATOR + LANGUAGE_TAG,
            LANGUAGE_TAG,
            LANGUAGE_TAG2,
            LANGUAGE_TAG
        );
    }

    @Override
    public ConverterTextToCollectionSetLocaleLanguageTagSet<ConverterContext> createConverter() {
        return ConverterTextToCollectionSetLocaleLanguageTagSet.instance();
    }

    @Override
    public ConverterContext createContext() {
        return new FakeConverterContext() {

            @Override
            public char valueSeparator() {
                return SEPARATOR;
            }

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

            private final Converter<FakeConverterContext> converter = Converters.collection(
                Lists.of(
                    Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString(),
                    Converters.textToLocaleLanguageTag()
                )
            );
        };
    }

    @Override
    Class<LocaleLanguageTagSet> collectionType() {
        return LocaleLanguageTagSet.class;
    }

    @Override
    LocaleLanguageTagSet emptySet() {
        return LocaleLanguageTagSet.EMPTY;
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToCollectionSetLocaleLanguageTagSet<ConverterContext>> type() {
        return Cast.to(ConverterTextToCollectionSetLocaleLanguageTagSet.class);
    }
}
