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
import walkingkooka.datetime.LocalDateList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class ConverterTextToListLocalDateListTest extends ConverterTextToListTestCase<LocalDateList, LocalDate, ConverterTextToListLocalDateList<ConverterContext>> {

    private final static LocalDate DATE = LocalDate.of(1999, 12, 31);

    private final static String DATE_STRING = "1999/12/31";

    private final static LocalDate DATE2 = LocalDate.of(2000, 1, 2);

    private final static String DATE_STRING2 = "2000/1/2";

    @Test
    public void testConvertString() {
        this.convertToListAndCheck(
            DATE_STRING,
            DATE
        );
    }

    @Test
    public void testConvertCharSequenceDate() {
        this.convertToListAndCheck(
            new StringBuilder(DATE_STRING),
            DATE
        );
    }

    @Test
    public void testConvertStringQuotedDate() {
        this.convertToListAndCheck(
            "\"1999/12/31\"",
            DATE
        );
    }

    @Test
    public void testConvertStringQuotedBackslashDate() {
        this.convertToListAndCheck(
            "\"\\1999/12/31\"",
            DATE
        );
    }

    @Test
    public void testConvertStringSpacesQuotedDate() {
        this.convertToListAndCheck(
            " \"1999/12/31\"",
            DATE
        );
    }

    @Test
    public void testConvertStringSpaceSpaceQuotedDate() {
        this.convertToListAndCheck(
            "  \"1999/12/31\"",
            DATE
        );
    }

    @Test
    public void testConvertStringQuotedDateSpaces() {
        this.convertToListAndCheck(
            "\"1999/12/31\" ",
            DATE
        );
    }

    @Test
    public void testConvertStringQuotedDateSpaceSpace() {
        this.convertToListAndCheck(
            "\"1999/12/31\" ",
            DATE
        );
    }

    @Test
    public void testConvertStringSpaceDate() {
        this.convertToListAndCheck(
            "1999/12/31",
            DATE
        );
    }

    @Test
    public void testConvertStringDateSpace() {
        this.convertToListAndCheck(
            "1999/12/31",
            DATE
        );
    }

    @Test
    public void testConvertDateSeparatorFails() {
        this.convertToListFails(
            "1999/12/31*"
        );
    }

    @Test
    public void testConvertDateSeparatorDate() {
        this.convertToListAndCheck(
            "1999/12/31*2000/1/2",
            DATE,
            DATE2
        );
    }

    @Test
    public void testConvertDateSeparatorDateSeparatorDate() {
        this.convertToListAndCheck(
            "1999/12/31*2000/1/2*1999/12/31",
            DATE,
            DATE2,
            DATE
        );
    }

    @Test
    public void testConvertDateSeparatorDateSeparatorDateExtraSpacesIgnored() {
        this.convertToListAndCheck(
            "1999/12/31 * 2000/1/2 *1999/12/31 ",
            DATE,
            DATE2,
            DATE
        );
    }

    @Test
    public void testConvertDateSeparatorDateSeparatorDateExtraSpacesIgnored2() {
        this.convertToListAndCheck(
            " 1999/12/31 * 2000/1/2 * 1999/12/31 ",
            DATE,
            DATE2,
            DATE
        );
    }

    @Override
    public ConverterTextToListLocalDateList<ConverterContext> createConverter() {
        return ConverterTextToListLocalDateList.instance();
    }

    @Override
    public ConverterContext createContext() {
        return new FakeConverterContext() {

            @Override
            public char valueSeparator() {
                return '*';
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
                    new ShortCircuitingConverter<>() {
                        @Override
                        public boolean canConvert(final Object value,
                                                  final Class<?> type,
                                                  final FakeConverterContext context) {
                            return value instanceof String && LocalDate.class == type;
                        }

                        @Override
                        public <T> Either<T, String> doConvert(final Object value,
                                                               final Class<T> type,
                                                               final FakeConverterContext context) {
                            return this.successfulConversion(
                                LocalDate.parse(
                                    (String) value,
                                    DateTimeFormatter.ofPattern("yyyy/M/d")
                                ),
                                type
                            );
                        }
                    }
                )
            );
        };
    }

    @Override
    Class<LocalDateList> listType() {
        return LocalDateList.class;
    }

    @Override
    LocalDateList emptyList() {
        return LocalDateList.EMPTY;
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToListLocalDateList<ConverterContext>> type() {
        return Cast.to(ConverterTextToListLocalDateList.class);
    }
}
