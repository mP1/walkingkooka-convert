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
     * {@see ConverterBooleanToNumber}
     */
    public static <C extends ConverterContext> Converter<C> booleanToNumber() {
        return ConverterBooleanToNumber.instance();
    }

    /**
     * {@see ConverterChain}
     */
    public static <C extends ConverterContext> Converter<C> chain(final Converter<C> first,
                                                                  final Class<?> intermediateType,
                                                                  final Converter<C> second) {
        return ConverterChain.with(
            first,
            intermediateType,
            second
        );
    }

    /**
     * {@see ConverterCharacterOrStringToString}
     */
    public static <C extends ConverterContext> Converter<C> characterOrStringToString() {
        return ConverterCharacterOrStringToString.instance();
    }

    /**
     * {@see ConverterCharacterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString}
     */
    public static <C extends ConverterContext> Converter<C> characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString() {
        return ConverterCharacterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString.instance();
    }

    /**
     * {@see ConverterCollection}
     */
    public static <C extends ConverterContext> Converter<C> collection(final List<Converter<C>> converters) {
        return ConverterCollection.with(converters);
    }

    /**
     * {@see ConverterCollectionToList}
     */
    public static <C extends ConverterContext> Converter<C> collectionToList() {
        return ConverterCollectionToList.instance();
    }

    /**
     * {@see ConverterCollectionTo}
     */
    public static <C extends ConverterContext> Converter<C> collectionTo() {
        return ConverterCollectionTo.instance();
    }

    /**
     * {@see ConverterCustomToString}
     */
    public static <C extends ConverterContext> Converter<C> customToString(final Converter<C> converter,
                                                                           final String toString) {
        return ConverterCustomToString.wrap(converter, toString);
    }

    /**
     * {@see FakeConverter}
     */
    public static <C extends ConverterContext> Converter<C> fake() {
        return new FakeConverter<>();
    }

    /**
     * {@see ConverterHasText}
     */
    public static <C extends ConverterContext> Converter<C> hasText() {
        return ConverterHasText.instance();
    }

    /**
     * {@see ConverterTemporalLocalDateToLocalDateTime}
     */
    public static <C extends ConverterContext> Converter<C> localDateToLocalDateTime() {
        return ConverterTemporalLocalDateToLocalDateTime.instance();
    }

    /**
     * {@see ConverterTemporalLocalDateToNumber}
     */
    public static <C extends ConverterContext> Converter<C> localDateToNumber() {
        return ConverterTemporalLocalDateToNumber.instance();
    }

    /**
     * {@see ConverterDateTimeFormatterLocalDateToStringDateTimeFormatter}
     */
    public static <C extends ConverterContext> Converter<C> localDateToString(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return ConverterDateTimeFormatterLocalDateToStringDateTimeFormatter.with(formatter);
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
    public static <C extends ConverterContext> Converter<C> localDateTimeToNumber() {
        return ConverterTemporalLocalDateTimeToNumber.instance();
    }

    /**
     * {@see ConverterDateTimeFormatterLocalDateTimeToStringDateTimeFormatter}
     */
    public static <C extends ConverterContext> Converter<C> localDateTimeToString(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return ConverterDateTimeFormatterLocalDateTimeToStringDateTimeFormatter.with(formatter);
    }

    /**
     * {@see ConverterLocaleToString}
     */
    public static <C extends ConverterContext> Converter<C> localeToText() {
        return ConverterLocaleToString.instance();
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
     * {@see ConverterDateTimeFormatterLocalTimeToStringDateTimeFormatter}
     */
    public static <C extends ConverterContext> Converter<C> localTimeToString(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return ConverterDateTimeFormatterLocalTimeToStringDateTimeFormatter.with(formatter);
    }

    /**
     * {@see ConverterPredicatedMapper}
     */
    public static <S, D, C extends ConverterContext> Converter<C> mapper(final Predicate<Object> source,
                                                                         final Predicate<Class<?>> target,
                                                                         final Function<S, D> converter) {
        return ConverterPredicatedMapper.with(source, target, converter);
    }

    /**
     * {@see ConverterNever}
     */
    public static <C extends ConverterContext> Converter<C> never() {
        return ConverterNever.instance();
    }

    /**
     * {@see ConverterNumberToBoolean}
     */
    public static <C extends ConverterContext> Converter<C> numberToBoolean() {
        return ConverterNumberToBoolean.instance();
    }

    /**
     * {@see ConverterNumberToLocalDate}
     */
    public static <C extends ConverterContext> Converter<C> numberToLocalDate() {
        return ConverterNumberToLocalDate.instance();
    }

    /**
     * {@see ConverterNumberToLocalDateTime}
     */
    public static <C extends ConverterContext> Converter<C> numberToLocalDateTime() {
        return ConverterNumberToLocalDateTime.instance();
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
     * {@see ConverterDecimalFormatNumberToString}
     */
    public static <C extends ConverterContext> Converter<C> numberToString(final Function<DecimalNumberContext, DecimalFormat> decimalFormat) {
        return ConverterDecimalFormatNumberToString.with(decimalFormat);
    }

    /**
     * {@see ConverterToObject}
     */
    public static <C extends ConverterContext> Converter<C> object() {
        return ConverterToObject.instance();
    }

    /**
     * {@see ConverterObjectToString}
     */
    public static <C extends ConverterContext> Converter<C> objectToString() {
        return ConverterObjectToString.instance();
    }

    /**
     * {@see ConverterOptionalTo}
     */
    public static <C extends ConverterContext> Converter<C> optionalTo() {
        return ConverterOptionalTo.instance();
    }

    /**
     * {@see ConverterParser}
     */
    public static <V,
        P extends ParserContext,
        C extends ConverterContext> Converter<C> parser(final Class<V> parserValueType,
                                                        final Parser<P> parser,
                                                        final Function<C, P> converterContextToParserContext,
                                                        final BiFunction<ParserToken, C, V> parserTokenToValue) {
        return ConverterParser.with(
            parserValueType,
            parser,
            converterContextToParserContext,
            parserTokenToValue
        );
    }

    /**
     * {@see ConverterSimple}
     */
    public static <C extends ConverterContext> Converter<C> simple() {
        return ConverterSimple.instance();
    }

    /**
     * {@see ConverterStringToCharacterOrString}
     */
    public static <C extends ConverterContext> Converter<C> stringToCharacterOrString() {
        return ConverterStringToCharacterOrString.instance();
    }

    /**
     * {@see ConverterDateTimeFormatterStringToLocalDateDateTimeFormatter}
     */
    public static <C extends ConverterContext> Converter<C> stringToLocalDate(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return ConverterDateTimeFormatterStringToLocalDateDateTimeFormatter.with(formatter);
    }

    /**
     * {@see ConverterDateTimeFormatterStringToLocalDateTimeDateTimeFormatter}
     */
    public static <C extends ConverterContext> Converter<C> stringToLocalDateTime(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return ConverterDateTimeFormatterStringToLocalDateTimeDateTimeFormatter.with(formatter);
    }

    /**
     * {@see ConverterDateTimeFormatterStringToLocalTimeDateTimeFormatter}
     */
    public static <C extends ConverterContext> Converter<C> stringToLocalTime(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return ConverterDateTimeFormatterStringToLocalTimeDateTimeFormatter.with(formatter);
    }

    /**
     * {@see ConverterDecimalFormatStringToNumber}
     */
    public static <C extends ConverterContext> Converter<C> stringToNumber(final Function<DecimalNumberContext, DecimalFormat> decimalFormat) {
        return ConverterDecimalFormatStringToNumber.with(decimalFormat);
    }

    /**
     * {@see ConverterTextToListBooleanList}
     */
    public static <C extends ConverterContext> Converter<C> textToBooleanList() {
        return ConverterTextToListBooleanList.instance();
    }

    /**
     * {@see ConverterTextToLineEnding}
     */
    public static <C extends ConverterContext> Converter<C> textToLineEnding() {
        return ConverterTextToLineEnding.instance();
    }

    /**
     * {@see ConverterTextToListCsvStringList}
     */
    public static <C extends ConverterContext> Converter<C> textToCsvStringList() {
        return ConverterTextToListCsvStringList.instance();
    }

    /**
     * {@see ConverterTextToListLocalDateList}
     */
    public static <C extends ConverterContext> Converter<C> textToLocalDateList() {
        return ConverterTextToListLocalDateList.instance();
    }

    /**
     * {@see ConverterTextToListLocalDateTimeList}
     */
    public static <C extends ConverterContext> Converter<C> textToLocalDateTimeList() {
        return ConverterTextToListLocalDateTimeList.instance();
    }

    /**
     * {@see ConverterTextToListLocalTimeList}
     */
    public static <C extends ConverterContext> Converter<C> textToLocalTimeList() {
        return ConverterTextToListLocalTimeList.instance();
    }

    /**
     * {@see ConverterTextToListNumberList}
     */
    public static <C extends ConverterContext> Converter<C> textToNumberList() {
        return ConverterTextToListNumberList.instance();
    }

    /**
     * {@see ConverterTextToListStringList}
     */
    public static <C extends ConverterContext> Converter<C> textToStringList() {
        return ConverterTextToListStringList.instance();
    }

    /**
     * {@see ConverterTextToZoneOffset}
     */
    public static <C extends ConverterContext> Converter<C> textToZoneOffset() {
        return ConverterTextToZoneOffset.instance();
    }

    /**
     * {@see ConverterToBoolean}
     */
    public static <V, C extends ConverterContext> Converter<C> toBoolean(final Predicate<Object> source,
                                                                         final Predicate<Class<?>> target,
                                                                         final Predicate<Object> trueValue,
                                                                         final V trueAnswer,
                                                                         final V falseAnswer) {
        return ConverterToBoolean.with(
            source,
            target,
            trueValue,
            trueAnswer,
            falseAnswer
        );
    }

    /**
     * Stop creation
     */
    private Converters() {
        throw new UnsupportedOperationException();
    }
}
