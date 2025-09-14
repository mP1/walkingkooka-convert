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
import walkingkooka.collect.list.BooleanList;
import walkingkooka.collect.list.CsvStringList;
import walkingkooka.collect.list.Lists;
import walkingkooka.collect.list.StringList;
import walkingkooka.collect.set.Sets;
import walkingkooka.datetime.LocalDateList;
import walkingkooka.datetime.LocalDateTimeList;
import walkingkooka.datetime.LocalTimeList;
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.math.DecimalNumberSymbols;
import walkingkooka.math.NumberList;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public final class ConverterCollectionToListTest extends ConverterTestCase2<ConverterCollectionToList<ConverterContext>> {

    // testConvertNull..................................................................................................

    @Test
    public void testConvertNullToList() {
        this.convertAndCheck(
            null,
            List.class
        );
    }

    @Test
    public void testConvertNullToBooleanList() {
        this.convertAndCheck(
            null,
            BooleanList.class
        );
    }

    @Test
    public void testConvertNullToLocalDateList() {
        this.convertAndCheck(
            null,
            LocalDateList.class
        );
    }

    @Test
    public void testConvertNullToLocalDateTimeList() {
        this.convertAndCheck(
            null,
            LocalDateTimeList.class
        );
    }

    @Test
    public void testConvertNullToLocalTimeList() {
        this.convertAndCheck(
            null,
            LocalTimeList.class
        );
    }

    @Test
    public void testConvertNullToNumberList() {
        this.convertAndCheck(
            null,
            NumberList.class
        );
    }

    @Test
    public void testConvertNullToCsvStringList() {
        this.convertAndCheck(
            null,
            CsvStringList.class
        );
    }

    @Test
    public void testConvertNullToStringList() {
        this.convertAndCheck(
            null,
            StringList.class
        );
    }

    // testConvertEmptyListTo...........................................................................................

    @Test
    public void testConvertEmptyListToList() {
        this.convertAndCheck(
            Lists.empty(),
            List.class
        );
    }

    @Test
    public void testConvertEmptyListToBooleanList() {
        this.convertAndCheck(
            Lists.empty(),
            BooleanList.class,
            BooleanList.EMPTY
        );
    }

    @Test
    public void testConvertEmptyListToLocalDateList() {
        this.convertAndCheck(
            Lists.empty(),
            LocalDateList.class,
            LocalDateList.EMPTY
        );
    }

    @Test
    public void testConvertEmptyListToLocalDateTimeList() {
        this.convertAndCheck(
            Lists.empty(),
            LocalDateTimeList.class,
            LocalDateTimeList.EMPTY
        );
    }

    @Test
    public void testConvertEmptyListToLocalTimeList() {
        this.convertAndCheck(
            Lists.empty(),
            LocalTimeList.class,
            LocalTimeList.EMPTY
        );
    }

    @Test
    public void testConvertEmptyListToNumberList() {
        this.convertAndCheck(
            Lists.empty(),
            NumberList.class,
            NumberList.EMPTY
        );
    }

    @Test
    public void testConvertEmptyListToCsvStringList() {
        this.convertAndCheck(
            Lists.empty(),
            CsvStringList.class,
            CsvStringList.EMPTY
        );
    }

    @Test
    public void testConvertEmptyListToStringList() {
        this.convertAndCheck(
            Lists.empty(),
            StringList.class,
            StringList.EMPTY
        );
    }

    // testConvertEmptySetTo...........................................................................................

    @Test
    public void testConvertEmptySetToList() {
        this.convertAndCheck(
            Sets.empty(),
            List.class,
            Lists.empty()
        );
    }

    @Test
    public void testConvertEmptySetToBooleanList() {
        this.convertAndCheck(
            Sets.empty(),
            BooleanList.class,
            BooleanList.EMPTY
        );
    }

    @Test
    public void testConvertEmptySetToLocalDateList() {
        this.convertAndCheck(
            Sets.empty(),
            LocalDateList.class,
            LocalDateList.EMPTY
        );
    }

    @Test
    public void testConvertEmptySetToLocalDateTimeList() {
        this.convertAndCheck(
            Sets.empty(),
            LocalDateTimeList.class,
            LocalDateTimeList.EMPTY
        );
    }

    @Test
    public void testConvertEmptySetToLocalTimeList() {
        this.convertAndCheck(
            Sets.empty(),
            LocalTimeList.class,
            LocalTimeList.EMPTY
        );
    }

    @Test
    public void testConvertEmptySetToNumberList() {
        this.convertAndCheck(
            Sets.empty(),
            NumberList.class,
            NumberList.EMPTY
        );
    }

    @Test
    public void testConvertEmptySetToCsvStringList() {
        this.convertAndCheck(
            Sets.empty(),
            CsvStringList.class,
            CsvStringList.EMPTY
        );
    }

    @Test
    public void testConvertEmptySetToStringList() {
        this.convertAndCheck(
            Sets.empty(),
            StringList.class,
            StringList.EMPTY
        );
    }

    // testConvertXXXToList.............................................................................................

    @Test
    public void testConvertBooleanListToList() {
        this.convertToListAndCheck(
            BooleanList.EMPTY.concat(true)
        );
    }

    @Test
    public void testConvertLocalDateListToList() {
        this.convertToListAndCheck(
            LocalDateList.EMPTY.concat(LocalDate.MIN)
        );
    }

    @Test
    public void testConvertLocalDateTimeListToList() {
        this.convertToListAndCheck(
            LocalDateTimeList.EMPTY.concat(LocalDateTime.MIN)
        );
    }

    @Test
    public void testConvertLocalTimeListToList() {
        this.convertToListAndCheck(
            LocalTimeList.EMPTY.concat(LocalTime.NOON)
        );
    }

    @Test
    public void testConvertNumberListToList() {
        this.convertToListAndCheck(
            NumberList.EMPTY.concat(123)
        );
    }

    @Test
    public void testConvertCsvStringListToList() {
        this.convertToListAndCheck(
            CsvStringList.EMPTY.concat("Hello")
        );
    }

    @Test
    public void testConvertStringListToList() {
        this.convertToListAndCheck(
            StringList.EMPTY.concat("Hello")
        );
    }

    private void convertToListAndCheck(final List<?> list) {
        this.convertAndCheck(
            list,
            List.class,
            list
        );
    }

    // testConvertXXXToXXXList..........................................................................................

    @Test
    public void testConvertBooleanListToBooleanList() {
        this.convertToXXXListAndCheck(
            BooleanList.EMPTY.concat(true)
        );
    }

    @Test
    public void testConvertLocalDateListToLocalDateList() {
        this.convertToXXXListAndCheck(
            LocalDateList.EMPTY.concat(LocalDate.MIN)
        );
    }

    @Test
    public void testConvertLocalDateTimeListToLocalDateTimeList() {
        this.convertToXXXListAndCheck(
            LocalDateTimeList.EMPTY.concat(LocalDateTime.MIN)
        );
    }

    @Test
    public void testConvertLocalTimeListToLocalTimeList() {
        this.convertToXXXListAndCheck(
            LocalTimeList.EMPTY.concat(LocalTime.NOON)
        );
    }

    @Test
    public void testConvertNumberListToNumberList() {
        this.convertToXXXListAndCheck(
            NumberList.EMPTY.concat(123)
        );
    }

    @Test
    public void testConvertCsvStringListToCsvStringList() {
        this.convertToXXXListAndCheck(
            CsvStringList.EMPTY.concat("Hello")
        );
    }

    @Test
    public void testConvertStringListToStringList() {
        this.convertToXXXListAndCheck(
            StringList.EMPTY.concat("Hello")
        );
    }

    private <T> void convertToXXXListAndCheck(final List<T> list) {
        this.convertAndCheck(
            list,
            Cast.to(list.getClass()),
            list
        );
    }

    // testConvertListXXXToXXXList......................................................................................

    @Test
    public void testConvertListOfBooleanToBooleanList() {
        this.convertAndCheck(
            Lists.of(
                true,
                false
            ),
            BooleanList.EMPTY.concat(true)
                .concat(false)
        );
    }

    @Test
    public void testConvertListOfLocalDateToLocalDateList() {
        final LocalDate date1 = LocalDate.MIN;
        final LocalDate date2 = LocalDate.MAX;

        this.convertAndCheck(
            Lists.of(
                date1,
                date2
            ),
            LocalDateList.EMPTY.concat(date1)
                .concat(date2)
        );
    }

    @Test
    public void testConvertListOfLocalDateTimeToLocalDateTimeList() {
        final LocalDateTime dateTime1 = LocalDateTime.MIN;
        final LocalDateTime dateTime2 = LocalDateTime.MAX;

        this.convertAndCheck(
            Lists.of(
                dateTime1,
                dateTime2
            ),
            LocalDateTimeList.EMPTY.concat(dateTime1)
                .concat(dateTime2)
        );
    }

    @Test
    public void testConvertListOfLocalTimeToLocalTimeList() {
        final LocalTime time1 = LocalTime.MIN;
        final LocalTime time2 = LocalTime.MAX;

        this.convertAndCheck(
            Lists.of(
                time1,
                time2
            ),
            LocalTimeList.EMPTY.concat(time1)
                .concat(time2)
        );
    }

    @Test
    public void testConvertListOfNumberToNumberList() {
        final Number number1 = 1;
        final Number number2 = 22;

        this.convertAndCheck(
            Lists.of(
                number1,
                number2
            ),
            NumberList.EMPTY.concat(number1)
                .concat(number2)
        );
    }

    @Test
    public void testConvertListOfStringToCsvStringList() {
        final String string1 = "a";
        final String string2 = "bb";

        this.convertAndCheck(
            Lists.of(
                string1,
                string2
            ),
            CsvStringList.EMPTY.concat(string1)
                .concat(string2)
        );
    }

    @Test
    public void testConvertListOfStringToStringList() {
        final String string1 = "a";
        final String string2 = "bb";

        this.convertAndCheck(
            Lists.of(
                string1,
                string2
            ),
            StringList.EMPTY.concat(string1)
                .concat(string2)
        );
    }

    // testConvertListStringToXXXList...................................................................................

    @Test
    public void testConvertListOfStringToBooleanList() {
        this.convertAndCheck(
            Lists.of(
                "true",
                "false"
            ),
            BooleanList.EMPTY.concat(true)
                .concat(false)
        );
    }

    @Test
    public void testConvertListOfStringToLocalDateList() {
        this.convertAndCheck(
            Lists.of(
                "1999/12/31",
                "2000/1/1"
            ),
            LocalDateList.EMPTY.concat(
                LocalDate.of(1999, 12, 31)
            ).concat(
                LocalDate.of(2000, 1, 1)
            )
        );
    }

    @Test
    public void testConvertListOfStringToLocalDateTimeList() {
        this.convertAndCheck(
            Lists.of(
                "1999/12/31 1:1",
                "2000/2/2 2:2"
            ),
            LocalDateTimeList.EMPTY.concat(
                LocalDateTime.of(1999, 12, 31, 1, 1, 0)
            ).concat(
                LocalDateTime.of(2000, 2, 2, 2, 2, 0)
            )
        );
    }

    @Test
    public void testConvertListOfStringToLocalTimeList() {
        this.convertAndCheck(
            Lists.of(
                "1:1",
                "2:2"
            ),
            LocalTimeList.EMPTY.concat(
                LocalTime.of(1, 1)
            ).concat(
                LocalTime.of(2, 2)
            )
        );
    }

    @Test
    public void testConvertListOfStringToNumberList() {
        this.convertAndCheck(
            Lists.of(
                "1",
                "22",
                "333"
            ),
            NumberList.EMPTY.concat(BigDecimal.ONE)
                .concat(BigDecimal.valueOf(22))
                .concat(BigDecimal.valueOf(333))
        );
    }

    // testConvertSetStringToXXXList...................................................................................

    @Test
    public void testConvertSetOfStringToBooleanList() {
        this.convertAndCheck(
            Sets.of(
                "true",
                "false"
            ),
            BooleanList.EMPTY.concat(true)
                .concat(false)
        );
    }

    @Test
    public void testConvertSetOfStringToLocalDateList() {
        this.convertAndCheck(
            Sets.of(
                "1999/12/31",
                "2000/1/1"
            ),
            LocalDateList.EMPTY.concat(
                LocalDate.of(1999, 12, 31)
            ).concat(
                LocalDate.of(2000, 1, 1)
            )
        );
    }

    @Test
    public void testConvertSetOfStringToLocalDateTimeList() {
        this.convertAndCheck(
            Sets.of(
                "1999/12/31 1:1",
                "2000/2/2 2:2"
            ),
            LocalDateTimeList.EMPTY.concat(
                LocalDateTime.of(1999, 12, 31, 1, 1, 0)
            ).concat(
                LocalDateTime.of(2000, 2, 2, 2, 2, 0)
            )
        );
    }

    @Test
    public void testConvertSetOfStringToLocalTimeList() {
        this.convertAndCheck(
            Sets.of(
                "1:1",
                "2:2"
            ),
            LocalTimeList.EMPTY.concat(
                LocalTime.of(1, 1)
            ).concat(
                LocalTime.of(2, 2)
            )
        );
    }

    @Test
    public void testConvertSetOfStringToNumberList() {
        this.convertAndCheck(
            Sets.of(
                "1",
                "22",
                "333"
            ),
            NumberList.EMPTY.concat(BigDecimal.ONE)
                .concat(BigDecimal.valueOf(22))
                .concat(BigDecimal.valueOf(333))
        );
    }

    @Override
    public ConverterCollectionToList<ConverterContext> createConverter() {
        return ConverterCollectionToList.instance();
    }

    @Override
    public ConverterContext createContext() {
        return new FakeConverterContext() {
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
                    Converters.simple(),
                    Converters.toBoolean(
                        (s) -> null == s || s instanceof String,
                        (t) -> Boolean.class == t,
                        (t) -> Boolean.parseBoolean(String.valueOf(t)),
                        Boolean.TRUE,
                        Boolean.FALSE
                    ),
                    Converters.stringToLocalDate(
                        (c) -> DateTimeFormatter.ofPattern("yyyy/M/d")
                    ),
                    Converters.stringToLocalDateTime(
                        (c) -> DateTimeFormatter.ofPattern("yyyy/M/d H:m")
                    ),
                    Converters.stringToLocalTime(
                        (c) -> DateTimeFormatter.ofPattern("H:m")
                    ),
                    Converters.stringToNumber(
                        (c) -> (DecimalFormat) DecimalFormat.getInstance()
                    )
                )
            );

            @Override
            public Locale locale() {
                return Locale.forLanguageTag("EN-AU");
            }

            @Override
            public int twoDigitYear() {
                return 50;
            }

            @Override
            public String currencySymbol() {
                return this.decimalNumberContext.currencySymbol();
            }

            @Override
            public char decimalSeparator() {
                return this.decimalNumberContext.decimalSeparator();
            }

            @Override
            public String exponentSymbol() {
                return this.decimalNumberContext.exponentSymbol();
            }

            @Override
            public char groupSeparator() {
                return this.decimalNumberContext.groupSeparator();
            }

            @Override
            public String infinitySymbol() {
                return this.decimalNumberContext.infinitySymbol();
            }

            @Override
            public char monetaryDecimalSeparator() {
                return this.decimalNumberContext.monetaryDecimalSeparator();
            }

            @Override
            public String nanSymbol() {
                return this.decimalNumberContext.nanSymbol();
            }

            @Override
            public char negativeSign() {
                return this.decimalNumberContext.negativeSign();
            }

            @Override
            public char percentSymbol() {
                return this.decimalNumberContext.percentSymbol();
            }

            @Override
            public char permillSymbol() {
                return this.decimalNumberContext.permillSymbol();
            }

            @Override
            public char positiveSign() {
                return this.decimalNumberContext.positiveSign();
            }

            @Override
            public char zeroDigit() {
                return this.decimalNumberContext.zeroDigit();
            }

            private final DecimalNumberContext decimalNumberContext = DecimalNumberContexts.basic(
                DecimalNumberSymbols.fromDecimalFormatSymbols(
                    '+',
                    new DecimalFormatSymbols(this.locale())
                ),
                this.locale(),
                MathContext.DECIMAL32
            );
        };
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "List to"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterCollectionToList<ConverterContext>> type() {
        return Cast.to(ConverterCollectionToList.class);
    }
}
