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
import walkingkooka.datetime.LocalTimeList;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class ConverterTextToListLocalTimeListTest extends ConverterTextToListTestCase<LocalTimeList, LocalTime, ConverterTextToListLocalTimeList<ConverterContext>> {

    private final static LocalTime DATE_TIME = LocalTime.of(1, 11, 58);

    private final static String DATE_TIME_STRING = "1:11:58";

    private final static LocalTime DATE_TIME2 = LocalTime.of(2, 22, 59);

    @Test
    public void testConvertString() {
        this.convertToListAndCheck(
            DATE_TIME_STRING,
            DATE_TIME
        );
    }

    @Test
    public void testConvertCharSequenceTime() {
        this.convertToListAndCheck(
            new StringBuilder(DATE_TIME_STRING),
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringQuotedTime() {
        this.convertToListAndCheck(
            "\"1:11:58\"",
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringQuotedBackslashTime() {
        this.convertToListAndCheck(
            "\"\\1:11:58\"",
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringSpacesQuotedTime() {
        this.convertToListAndCheck(
            " \"1:11:58\"",
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringSpaceSpaceQuotedTime() {
        this.convertToListAndCheck(
            "  \"1:11:58\"",
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringQuotedTimeSpaces() {
        this.convertToListAndCheck(
            "\"1:11:58\" ",
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringQuotedTimeSpaceSpace() {
        this.convertToListAndCheck(
            "\"1:11:58\" ",
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringSpaceTime() {
        this.convertToListAndCheck(
            "1:11:58",
            DATE_TIME
        );
    }

    @Test
    public void testConvertStringTimeSpace() {
        this.convertToListAndCheck(
            "1:11:58",
            DATE_TIME
        );
    }

    @Test
    public void testConvertTimeSeparatorFails() {
        this.convertToListFails(
            "1:11:58*"
        );
    }

    @Test
    public void testConvertTimeSeparatorTime() {
        this.convertToListAndCheck(
            "1:11:58*2:22:59",
            DATE_TIME,
            DATE_TIME2
        );
    }

    @Test
    public void testConvertTimeSeparatorTimeSeparatorTime() {
        this.convertToListAndCheck(
            "1:11:58*2:22:59*1:11:58",
            DATE_TIME,
            DATE_TIME2,
            DATE_TIME
        );
    }

    @Test
    public void testConvertTimeSeparatorTimeSeparatorTimeExtraSpacesIgnored() {
        this.convertToListAndCheck(
            "1:11:58 * 2:22:59 *1:11:58 ",
            DATE_TIME,
            DATE_TIME2,
            DATE_TIME
        );
    }

    @Test
    public void testConvertTimeSeparatorTimeSeparatorTimeExtraSpacesIgnored2() {
        this.convertToListAndCheck(
            " 1:11:58 * 2:22:59 * 1:11:58 ",
            DATE_TIME,
            DATE_TIME2,
            DATE_TIME
        );
    }

    @Override
    public ConverterTextToListLocalTimeList<ConverterContext> createConverter() {
        return ConverterTextToListLocalTimeList.instance();
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
                            return value instanceof String && LocalTime.class == type;
                        }

                        @Override
                        public <T> Either<T, String> doConvert(final Object value,
                                                               final Class<T> type,
                                                               final FakeConverterContext context) {
                            return this.successfulConversion(
                                LocalTime.parse(
                                    (String) value,
                                    DateTimeFormatter.ofPattern("H:m:s")
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
    Class<LocalTimeList> listType() {
        return LocalTimeList.class;
    }

    @Override
    LocalTimeList emptyList() {
        return LocalTimeList.EMPTY;
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToListLocalTimeList<ConverterContext>> type() {
        return Cast.to(ConverterTextToListLocalTimeList.class);
    }
}
