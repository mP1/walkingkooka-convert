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

import walkingkooka.datetime.DateTimeContext;
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.reflect.PublicStaticHelper;
import walkingkooka.text.cursor.parser.Parser;
import walkingkooka.text.cursor.parser.ParserContext;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Factory methods for numerous {@link Converter converters}.
 */
public final class Converters implements PublicStaticHelper {

    /**
     * Parameter to use as the offset for java date/datetimes
     */
    public final static long JAVA_EPOCH_OFFSET = 0;

    /**
     * Constant to use to convert date/datetimes to an excel/spreadsheet number.
     * A numeric value of 0 when formatted as a date/datetime shows a date component of 1899/12/30.
     * <a href="https://support.microsoft.com/en-us/office/date-systems-in-excel-e7fe7167-48a9-4b96-bb53-5612a800b487">Date systems in Excel</a>
     */
    public final static long EXCEL_1900_DATE_SYSTEM_OFFSET = LocalDate.of(1899, 12, 30).toEpochDay();

    /**
     * Hours per day.
     */
    private static final long HOURS_PER_DAY = 24;

    /**
     * Mins per day.
     */
    private static final long MINUTES_PER_HOUR = 60;

    /**
     * Seconds per day.
     */
    private static final long SECONDS_PER_MINUTE = 60;

    /**
     * Nanos per second.
     */
    static final long NANOS_PER_SECOND = 1000_000_000L;

    /**
     * Nanos per minute.
     */
    private static final long NANOS_PER_MINUTE = NANOS_PER_SECOND * SECONDS_PER_MINUTE;

    /**
     * Nanos per hour.
     */
    private static final long NANOS_PER_HOUR = NANOS_PER_MINUTE * MINUTES_PER_HOUR;

    /**
     * Nanos per day.
     */
    static final long NANOS_PER_DAY = NANOS_PER_HOUR * HOURS_PER_DAY;

    /**
     * [@see ConverterBigDecimalBoolean}
     */
    public static <C extends ConverterContext> Converter<C> bigDecimalBoolean() {
        return ConverterBigDecimalBoolean.instance();
    }

    /**
     * [@see BooleanTrueFalseConverter}
     */
    public static <S, D> Converter booleanTrueFalse(final Predicate<Object> source,
                                                    final Predicate<Object> falseValue,
                                                    final Predicate<Class<?>> target,
                                                    final D trueAnswer,
                                                    final D falseAnswer) {
        return BooleanTrueFalseConverter.with(source,
                falseValue,
                target,
                trueAnswer,
                falseAnswer);
    }

    /**
     * [@see ConverterBooleanNumber}
     */
    public static <C extends ConverterContext> Converter<C> booleanNumber() {
        return ConverterBooleanNumber.instance();
    }

    /**
     * {@see ConverterCollection}
     */
    public static <C extends ConverterContext> Converter<C> collection(final List<Converter<C>> converters) {
        return ConverterCollection.with(converters);
    }

    /**
     * {@see CustomToStringConverter}
     */
    public static <C extends ConverterContext> Converter<C> customToString(final Converter converter, final String toString) {
        return CustomToStringConverter.wrap(converter, toString);
    }

    /**
     * {@see FakeConverter}
     */
    public static <C extends ConverterContext> Converter<C> fake() {
        return new FakeConverter();
    }

    /**
     * [@see FunctionConverter}
     */
    public static <S, D> Converter function(final Predicate<Object> source,
                                            final Predicate<Class<?>> target,
                                            final Function<S, D> converter) {
        return FunctionConverter.with(source, target, converter);
    }

    /**
     * {@see LocalDateLocalDateTimeConverter}
     */
    public static <C extends ConverterContext> Converter<C> localDateLocalDateTime() {
        return walkingkooka.convert.ConverterTemporalLocalDateLocalDateTime.instance();
    }

    /**
     * {@see ConverterTemporalLocalDateNumber}
     */
    public static <C extends ConverterContext> Converter<C> localDateNumber(final long offset) {
        return walkingkooka.convert.ConverterTemporalLocalDateNumber.with(offset);
    }

    /**
     * {@see DateTimeFormatterConverterLocalDateString}
     */
    public static <C extends ConverterContext> Converter<C> localDateString(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return DateTimeFormatterConverterLocalDateString.with(formatter);
    }

    /**
     * {@see ConverterTemporalLocalDateTimeLocalDate}
     */
    public static <C extends ConverterContext> Converter<C> localDateTimeLocalDate() {
        return ConverterTemporalLocalDateTimeLocalDate.instance();
    }

    /**
     * {@see ConverterTemporalLocalDateTimeLocalTime}
     */
    public static <C extends ConverterContext> Converter<C> localDateTimeLocalTime() {
        return ConverterTemporalLocalDateTimeLocalTime.instance();
    }

    /**
     * {@see ConverterTemporalLocalDateTimeNumber}
     */
    public static <C extends ConverterContext> Converter<C> localDateTimeNumber(final long offset) {
        return walkingkooka.convert.ConverterTemporalLocalDateTimeNumber.with(offset);
    }

    /**
     * {@see DateTimeFormatterConverterLocalDateTimeString}
     */
    public static <C extends ConverterContext> Converter<C> localDateTimeString(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return DateTimeFormatterConverterLocalDateTimeString.with(formatter);
    }

    /**
     * {@see ConverterLocalTimeLocalDateTime}
     */
    public static <C extends ConverterContext> Converter<C> localTimeLocalDateTime() {
        return ConverterLocalTimeLocalDateTime.instance();
    }

    /**
     * {@see ConverterLocalTimeNumber}
     */
    public static <C extends ConverterContext> Converter<C> localTimeNumber() {
        return ConverterLocalTimeNumber.instance();
    }

    /**
     * {@see DateTimeFormatterConverterLocalTimeString}
     */
    public static <C extends ConverterContext> Converter<C> localTimeString(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return DateTimeFormatterConverterLocalTimeString.with(formatter);
    }

    /**
     * {@see ConverterNever}
     */
    public static <C extends ConverterContext> Converter<C> never() {
        return ConverterNever.instance();
    }

    /**
     * {@see ConverterNumberLocalDate}
     */
    public static <C extends ConverterContext> Converter<C> numberLocalDate(final long offset) {
        return ConverterNumberLocalDate.with(offset);
    }

    /**
     * {@see ConverterNumberLocalDateTime}
     */
    public static <C extends ConverterContext> Converter<C> numberLocalDateTime(final long offset) {
        return ConverterNumberLocalDateTime.with(offset);
    }

    /**
     * {@see ConverterNumberLocalTime}
     */
    public static <C extends ConverterContext> Converter<C> numberLocalTime() {
        return ConverterNumberLocalTime.instance();
    }

    /**
     * {@see ConverterNumberNumber}
     */
    public static <C extends ConverterContext> Converter<C> numberNumber() {
        return ConverterNumberNumber.instance();
    }

    /**
     * {@see DecimalFormatConverterNumberString}
     */
    public static <C extends ConverterContext> Converter<C> numberString(final Function<DecimalNumberContext, DecimalFormat> decimalFormat) {
        return DecimalFormatConverterNumberString.with(decimalFormat);
    }

    /**
     * [@see ConverterObjectString}
     */
    public static <C extends ConverterContext> Converter<C> objectString() {
        return ConverterObjectString.instance();
    }
    
    /**
     * {@see ParserConverter}
     */
    public static <V, C extends ParserContext> Converter parser(final Class<V> type,
                                                                final Parser<C> parser,
                                                                final Function<ConverterContext, C> context) {
        return ParserConverter.with(type, parser, context);
    }

    /**
     * {@see SimpleConverter}
     */
    public static <C extends ConverterContext> Converter<C> simple() {
        return SimpleConverter.instance();
    }

    /**
     * {@see DateTimeFormatterConverterStringLocalDate}
     */
    public static <C extends ConverterContext> Converter<C> stringLocalDate(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return DateTimeFormatterConverterStringLocalDate.with(formatter);
    }

    /**
     * {@see DateTimeFormatterConverterStringLocalDateTime}
     */
    public static <C extends ConverterContext> Converter<C> stringLocalDateTime(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return DateTimeFormatterConverterStringLocalDateTime.with(formatter);
    }

    /**
     * {@see DateTimeFormatterConverterStringLocalTime}
     */
    public static <C extends ConverterContext> Converter<C> stringLocalTime(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return DateTimeFormatterConverterStringLocalTime.with(formatter);
    }

    /**
     * {@see DecimalFormatConverterStringNumber}
     */
    public static <C extends ConverterContext> Converter<C> stringNumber(final Function<DecimalNumberContext, DecimalFormat> decimalFormat) {
        return DecimalFormatConverterStringNumber.with(decimalFormat);
    }

    /**
     * {@see ConverterNumberBoolean}
     */
    public static <C extends ConverterContext> Converter<C> truthyNumberBoolean() {
        return ConverterNumberBoolean.instance();
    }

    /**
     * Stop creation
     */
    private Converters() {
        throw new UnsupportedOperationException();
    }
}
