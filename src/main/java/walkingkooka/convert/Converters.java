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
import walkingkooka.text.cursor.parser.ParserToken;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.BiFunction;
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
     * Constant to use to convert date/datetimes to an excel/spreadsheet number.
     * A numeric value of 0 when formatted as a date/datetime shows a date component of 1904/1/1.
     * <a href="https://support.microsoft.com/en-us/office/date-systems-in-excel-e7fe7167-48a9-4b96-bb53-5612a800b487">Date systems in Excel</a>
     */
    public final static long EXCEL_1904_DATE_SYSTEM_OFFSET = LocalDate.of(1904, 1, 1).toEpochDay();

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
     * {@see ConverterBigDecimalToBoolean}
     */
    public static <C extends ConverterContext> Converter<C> bigDecimalToBoolean() {
        return ConverterBigDecimalToBoolean.instance();
    }

    /**
     * {@see ConverterBooleanToNumber}
     */
    public static <C extends ConverterContext> Converter<C> booleanToNumber() {
        return ConverterBooleanToNumber.instance();
    }

    /**
     * {@see ConverterCharacterOrStringThen}
     */
    public static <C extends ConverterContext> Converter<C> characterOrStringThen(final Converter<C> converter) {
        return ConverterCharacterOrStringThen.with(converter);
    }

    /**
     * {@see ConverterToStringOrCharacter}
     */
    public static <C extends ConverterContext> Converter<C> thenStringOrCharacter(final Converter<C> converter) {
        return ConverterToStringOrCharacter.with(converter);
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
    public static <C extends ConverterContext> Converter<C> customToString(final Converter<C> converter,
                                                                           final String toString) {
        return CustomToStringConverter.wrap(converter, toString);
    }

    /**
     * {@see FakeConverter}
     */
    public static <C extends ConverterContext> Converter<C> fake() {
        return new FakeConverter<>();
    }

    /**
     * {@see LocalDateLocalDateTimeConverter}
     */
    public static <C extends ConverterContext> Converter<C> localDateToLocalDateTime() {
        return ConverterTemporalLocalDateToLocalDateTime.instance();
    }

    /**
     * {@see ConverterTemporalLocalDateToNumber}
     */
    public static <C extends ConverterContext> Converter<C> localDateToNumber(final long offset) {
        return ConverterTemporalLocalDateToNumber.with(offset);
    }

    /**
     * {@see DateTimeFormatterConverterLocalDateToString}
     */
    public static <C extends ConverterContext> Converter<C> localDateToString(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return DateTimeFormatterConverterLocalDateToString.with(formatter);
    }

    /**
     * {@see ConverterTemporalLocalDateTimeToLocalDate}
     */
    public static <C extends ConverterContext> Converter<C> localDateTimeToLocalDate() {
        return ConverterTemporalLocalDateTimeToLocalDate.instance();
    }

    /**
     * {@see ConverterTemporalLocalDateTimeToLocalTime}
     */
    public static <C extends ConverterContext> Converter<C> localDateTimeToLocalTime() {
        return ConverterTemporalLocalDateTimeToLocalTime.instance();
    }

    /**
     * {@see ConverterTemporalLocalDateTimeToNumber}
     */
    public static <C extends ConverterContext> Converter<C> localDateTimeToNumber(final long offset) {
        return ConverterTemporalLocalDateTimeToNumber.with(offset);
    }

    /**
     * {@see DateTimeFormatterConverterLocalDateTimeToString}
     */
    public static <C extends ConverterContext> Converter<C> localDateTimeToString(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return DateTimeFormatterConverterLocalDateTimeToString.with(formatter);
    }

    /**
     * {@see ConverterLocalTimeToLocalDateTime}
     */
    public static <C extends ConverterContext> Converter<C> localTimeToLocalDateTime() {
        return ConverterLocalTimeToLocalDateTime.instance();
    }

    /**
     * {@see ConverterLocalTimeToNumber}
     */
    public static <C extends ConverterContext> Converter<C> localTimeToNumber() {
        return ConverterLocalTimeToNumber.instance();
    }

    /**
     * {@see DateTimeFormatterConverterLocalTimeToString}
     */
    public static <C extends ConverterContext> Converter<C> localTimeToString(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return DateTimeFormatterConverterLocalTimeToString.with(formatter);
    }

    /**
     * {@see PredicatedMapperConverter}
     */
    public static <S, D, C extends ConverterContext> Converter<C> mapper(final Predicate<Object> source,
                                                                         final Predicate<Class<?>> target,
                                                                         final Function<S, D> converter) {
        return PredicatedMapperConverter.with(source, target, converter);
    }

    /**
     * {@see ConverterNever}
     */
    public static <C extends ConverterContext> Converter<C> never() {
        return ConverterNever.instance();
    }

    /**
     * {@see ConverterNumberToLocalDate}
     */
    public static <C extends ConverterContext> Converter<C> numberToLocalDate(final long offset) {
        return ConverterNumberToLocalDate.with(offset);
    }

    /**
     * {@see ConverterNumberToLocalDateTime}
     */
    public static <C extends ConverterContext> Converter<C> numberToLocalDateTime(final long offset) {
        return ConverterNumberToLocalDateTime.with(offset);
    }

    /**
     * {@see ConverterNumberToLocalTime}
     */
    public static <C extends ConverterContext> Converter<C> numberToLocalTime() {
        return ConverterNumberToLocalTime.instance();
    }

    /**
     * {@see ConverterNumberToNumber}
     */
    public static <C extends ConverterContext> Converter<C> numberToNumber() {
        return ConverterNumberToNumber.instance();
    }

    /**
     * {@see DecimalFormatConverterNumberToString}
     */
    public static <C extends ConverterContext> Converter<C> numberToString(final Function<DecimalNumberContext, DecimalFormat> decimalFormat) {
        return DecimalFormatConverterNumberToString.with(decimalFormat);
    }

    /**
     * {@see ConverterObject}
     */
    public static <C extends ConverterContext> Converter<C> object() {
        return ConverterObject.instance();
    }
    
    /**
     * {@see ConverterObjectString}
     */
    public static <C extends ConverterContext> Converter<C> objectString() {
        return ConverterObjectString.instance();
    }
    
    /**
     * {@see ParserConverter}
     */
    public static <V,
            P extends ParserContext,
            C extends ConverterContext> Converter<C> parser(final Class<V> parserValueType,
                                                            final Parser<P> parser,
                                                            final Function<C, P> converterContextToParserContext,
                                                            final BiFunction<ParserToken, C, V> parserTokenToValue) {
        return ParserConverter.with(
                parserValueType,
                parser,
                converterContextToParserContext,
                parserTokenToValue
        );
    }

    /**
     * {@see SimpleConverter}
     */
    public static <C extends ConverterContext> Converter<C> simple() {
        return SimpleConverter.instance();
    }

    /**
     * {@see ConverterStringCharacter}
     */
    public static <C extends ConverterContext> Converter<C> stringCharacter() {
        return ConverterStringCharacter.instance();
    }

    /**
     * {@see DateTimeFormatterConverterStringToLocalDate}
     */
    public static <C extends ConverterContext> Converter<C> stringToLocalDate(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return DateTimeFormatterConverterStringToLocalDate.with(formatter);
    }

    /**
     * {@see DateTimeFormatterConverterStringToLocalDateTime}
     */
    public static <C extends ConverterContext> Converter<C> stringToLocalDateTime(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return DateTimeFormatterConverterStringToLocalDateTime.with(formatter);
    }

    /**
     * {@see DateTimeFormatterConverterStringToLocalTime}
     */
    public static <C extends ConverterContext> Converter<C> stringToLocalTime(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return DateTimeFormatterConverterStringToLocalTime.with(formatter);
    }

    /**
     * {@see DecimalFormatConverterStringToNumber}
     */
    public static <C extends ConverterContext> Converter<C> stringToNumber(final Function<DecimalNumberContext, DecimalFormat> decimalFormat) {
        return DecimalFormatConverterStringToNumber.with(decimalFormat);
    }

    /**
     * {@see ToBooleanConverter}
     */
    public static <V, C extends ConverterContext> Converter<C> toBoolean(final Predicate<Object> source,
                                                                         final Predicate<Class<?>> target,
                                                                         final Predicate<Object> trueValue,
                                                                         final V trueAnswer,
                                                                         final V falseAnswer) {
        return ToBooleanConverter.with(
                source,
                target,
                trueValue,
                trueAnswer,
                falseAnswer
        );
    }

    /**
     * {@see ConverterNumberToBoolean}
     */
    public static <C extends ConverterContext> Converter<C> truthyNumberBoolean() {
        return ConverterNumberToBoolean.instance();
    }

    /**
     * Stop creation
     */
    private Converters() {
        throw new UnsupportedOperationException();
    }
}
