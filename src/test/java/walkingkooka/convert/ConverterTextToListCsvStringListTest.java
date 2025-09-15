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
import walkingkooka.collect.list.CsvStringList;
import walkingkooka.collect.list.Lists;

public final class ConverterTextToListCsvStringListTest extends ConverterTextToListTestCase<CsvStringList, String, ConverterTextToListCsvStringList<ConverterContext>> {

    private final static String STRING = "abc";

    private final static String STRING2 = "def ghi";

    private final static char SEPARATOR = ',';

    @Test
    public void testConvertString() {
        this.convertToListAndCheck(
            STRING,
            STRING
        );
    }

    @Test
    public void testConvertStringSurroundingSpacesTrimmed() {
        this.convertToListAndCheck(
            " " + STRING2 + " ",
            STRING2
        );
    }

    @Test
    public void testConvertCharSequenceString() {
        this.convertToListAndCheck(
            new StringBuilder(STRING),
            STRING
        );
    }

    @Test
    public void testConvertStringQuotedString() {
        this.convertToListAndCheck(
            "\"" + STRING + "\"",
            STRING
        );
    }

    @Test
    public void testConvertStringSeparatorString() {
        this.convertToListAndCheck(
            STRING + SEPARATOR + STRING2,
            STRING,
            STRING2
        );
    }

    @Test
    public void testConvertStringSeparatorStringSeparatorString() {
        this.convertToListAndCheck(
            STRING + SEPARATOR + STRING2 + SEPARATOR + STRING,
            STRING,
            STRING2,
            STRING
        );
    }

    @Override
    public ConverterTextToListCsvStringList<ConverterContext> createConverter() {
        return ConverterTextToListCsvStringList.instance();
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
                    Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString()
                )
            );
        };
    }

    @Override
    Class<CsvStringList> listType() {
        return CsvStringList.class;
    }

    @Override
    CsvStringList emptyList() {
        return CsvStringList.EMPTY;
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToListCsvStringList<ConverterContext>> type() {
        return Cast.to(ConverterTextToListCsvStringList.class);
    }
}
