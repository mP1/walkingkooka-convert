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
import walkingkooka.datetime.LocalDateTimeList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class ConverterTextToListLocalDateTimeListTest extends ConverterTextToListTestCase<LocalDateTimeList, LocalDateTime, ConverterTextToListLocalDateTimeList<ConverterContext>> {

    private final static LocalDateTime DATE_TIME = LocalDateTime.of(1999, 12, 31, 1, 11, 58);

    private final static String DATE_TIME_STRING = "1999/12/31 1:11:58";

    private final static LocalDateTime DATE_TIME2 = LocalDateTime.of(2000, 1, 2, 2, 22, 59);

    @Test
    public void testConvertString() {
        this.convertToListAndCheck(
            DATE_TIME_STRING,
            DATE_TIME
        );
    }

    @Test
    public void testConvertCharSequenceDateTime() {
        this.convertToListAndCheck(
            new StringBuilder(DATE_TIME_STRING),
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringQuotedDateTime() {
        this.convertToListAndCheck(
            "\"1999/12/31 1:11:58\"",
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringQuotedBackslashDateTime() {
        this.convertToListAndCheck(
            "\"\\1999/12/31 1:11:58\"",
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringSpacesQuotedDateTime() {
        this.convertToListAndCheck(
            " \"1999/12/31 1:11:58\"",
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringSpaceSpaceQuotedDateTime() {
        this.convertToListAndCheck(
            "  \"1999/12/31 1:11:58\"",
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringQuotedDateTimeSpaces() {
        this.convertToListAndCheck(
            "\"1999/12/31 1:11:58\" ",
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringQuotedDateTimeSpaceSpace() {
        this.convertToListAndCheck(
            "\"1999/12/31 1:11:58\" ",
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringSpaceDateTime() {
        this.convertToListAndCheck(
            "1999/12/31 1:11:58",
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringDateTimeSpace() {
        this.convertToListAndCheck(
            "1999/12/31 1:11:58",
            DATE_TIME
        );
    }

    @Test
    public void testConvertDateTimeSeparatorFails() {
        this.convertToListFails(
            "1999/12/31 1:11:58*"
        );
    }

    @Test
    public void testConvertDateTimeSeparatorDateTime() {
        this.convertToListAndCheck(
            "1999/12/31 1:11:58*2000/1/2 2:22:59",
            DATE_TIME,
            DATE_TIME2
        );
    }

    @Test
    public void testConvertDateTimeSeparatorDateTimeSeparatorDateTime() {
        this.convertToListAndCheck(
            "1999/12/31 1:11:58*2000/1/2 2:22:59*1999/12/31 1:11:58",
            DATE_TIME,
            DATE_TIME2,
            DATE_TIME
        );
    }

    @Test
    public void testConvertDateTimeSeparatorDateTimeSeparatorDateTimeExtraSpacesIgnored() {
        this.convertToListAndCheck(
            "1999/12/31 1:11:58 * 2000/1/2 2:22:59 *1999/12/31 1:11:58 ",
            DATE_TIME,
            DATE_TIME2,
            DATE_TIME
        );
    }

    @Test
    public void testConvertDateTimeSeparatorDateTimeSeparatorDateTimeExtraSpacesIgnored2() {
        this.convertToListAndCheck(
            " 1999/12/31 1:11:58 * 2000/1/2 2:22:59 * 1999/12/31 1:11:58 ",
            DATE_TIME,
            DATE_TIME2,
            DATE_TIME
        );
    }

    @Override
    public ConverterTextToListLocalDateTimeList<ConverterContext> createConverter() {
        return ConverterTextToListLocalDateTimeList.instance();
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
                            return value instanceof String && LocalDateTime.class == type;
                        }

                        @Override
                        public <T> Either<T, String> doConvert(final Object value,
                                                               final Class<T> type,
                                                               final FakeConverterContext context) {
                            return this.successfulConversion(
                                LocalDateTime.parse(
                                    (String) value,
                                    DateTimeFormatter.ofPattern("yyyy/M/d H:m:s")
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
    Class<LocalDateTimeList> listType() {
        return LocalDateTimeList.class;
    }

    @Override
    LocalDateTimeList emptyList() {
        return LocalDateTimeList.EMPTY;
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToListLocalDateTimeList<ConverterContext>> type() {
        return Cast.to(ConverterTextToListLocalDateTimeList.class);
    }
}
