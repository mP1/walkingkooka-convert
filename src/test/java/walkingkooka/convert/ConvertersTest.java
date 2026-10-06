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
import walkingkooka.collect.list.Lists;
import walkingkooka.reflect.MethodAttributes;
import walkingkooka.reflect.PublicStaticHelperTesting;
import walkingkooka.text.printer.TreePrintable;
import walkingkooka.text.printer.TreePrintableTesting;

import java.lang.reflect.Method;
import java.math.MathContext;
import java.util.List;

public final class ConvertersTest implements PublicStaticHelperTesting<Converters>,
    TreePrintableTesting {

    @Test
    public void testExcelConstantsDifference() {
        this.checkEquals(Converters.EXCEL_1904_DATE_SYSTEM_OFFSET - Converters.EXCEL_1900_DATE_SYSTEM_OFFSET, 1462L);
    }

    @Test
    public void testConverterCollectionWithAllConvertersPrintTree() throws Exception {
        final List<Converter<ConverterContext>> converters = Lists.array();

        for (final Method method : Converters.class.getMethods()) {
            if (false == MethodAttributes.STATIC.is(method)) {
                continue;
            }

            if (false == method.getReturnType().equals(Converter.class)) {
                continue;
            }

            if (method.getParameterCount() != 0) {
                continue;
            }

            if (method.getName().equals("fake")) {
                continue;
            }

            converters.add(
                (Converter<ConverterContext>) method.invoke(null)
            );
        }

        converters.sort(
            (Converter<?> left, Converter<?> right) -> left.toString().compareTo(right.toString())
        );

        this.treePrintAndCheck(
            (TreePrintable) Converters.collection(converters),
            "ConverterCollection\n" +
                "  * to Object (walkingkooka.convert.ConverterToObject)\n" +
                "  * to String (walkingkooka.convert.ConverterObjectToString)\n" +
                "  Binary to TEXT (walkingkooka.convert.ConverterBinaryToString)\n" +
                "  Boolean to Number (walkingkooka.convert.ConverterBooleanToNumber)\n" +
                "  Character or CharSequence or HasText or String to Character or CharSequence or String (walkingkooka.convert.ConverterCharacterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString)\n" +
                "  Character or String to String (walkingkooka.convert.ConverterCharacterOrStringToString)\n" +
                "  Collection to (walkingkooka.convert.ConverterCollectionTo)\n" +
                "  Collection to List (walkingkooka.convert.ConverterCollectionToList)\n" +
                "  CurrencyCode to Currency (walkingkooka.convert.ConverterCurrencyCodeToCurrency)\n" +
                "  CurrencyValue to (walkingkooka.convert.ConverterCurrencyValueTo)\n" +
                "  CurrencyValue to Number (walkingkooka.convert.ConverterCurrencyValueToNumber)\n" +
                "  HasValue to (walkingkooka.convert.ConverterToValue)\n" +
                "  LocalDate to LocalDateTime (walkingkooka.convert.ConverterTemporalLocalDateToLocalDateTime)\n" +
                "  LocalDate to Number (walkingkooka.convert.ConverterTemporalLocalDateToNumber)\n" +
                "  LocalDateTime to LocalDate (walkingkooka.convert.ConverterTemporalLocalDateTimeToLocalDate)\n" +
                "  LocalDateTime to LocalTime (walkingkooka.convert.ConverterTemporalLocalDateTimeToLocalTime)\n" +
                "  LocalDateTime to Number (walkingkooka.convert.ConverterTemporalLocalDateTimeToNumber)\n" +
                "  LocalTime to LocalDateTime (walkingkooka.convert.ConverterLocalTimeToLocalDateTime)\n" +
                "  LocalTime to Number (walkingkooka.convert.ConverterLocalTimeToNumber)\n" +
                "  Locale to String (walkingkooka.convert.ConverterLocaleToString)\n" +
                "  Number to Boolean (walkingkooka.convert.ConverterNumberToBoolean)\n" +
                "  Number to CurrencyValue (walkingkooka.convert.ConverterNumberToCurrencyValue)\n" +
                "  Number to LocalDate (walkingkooka.convert.ConverterNumberToLocalDate)\n" +
                "  Number to LocalDateTime (walkingkooka.convert.ConverterNumberToLocalDateTime)\n" +
                "  Number to LocalTime (walkingkooka.convert.ConverterNumberToLocalTime)\n" +
                "  Number to Number (walkingkooka.convert.ConverterNumberToNumber)\n" +
                "  Optional to (walkingkooka.convert.ConverterOptionalTo)\n" +
                "  Properties to DateTimeSymbols (walkingkooka.convert.ConverterPropertiesToDateTimeSymbols)\n" +
                "  Properties to DecimalNumberSymbols (walkingkooka.convert.ConverterPropertiesToDecimalNumberSymbols)\n" +
                "  String to Character or String (walkingkooka.convert.ConverterStringToCharacterOrString)\n" +
                "  TEXT to Binary (walkingkooka.convert.ConverterTextToBinary)\n" +
                "  TEXT to BooleanList (walkingkooka.convert.ConverterTextToCollectionListBooleanList)\n" +
                "  TEXT to Charset (walkingkooka.convert.ConverterTextToCharset)\n" +
                "  TEXT to CsvStringList (walkingkooka.convert.ConverterTextToCollectionListCsvStringList)\n" +
                "  TEXT to CsvStringSet (walkingkooka.convert.ConverterTextToCollectionSetCsvStringSet)\n" +
                "  TEXT to Currency (walkingkooka.convert.ConverterTextToCurrency)\n" +
                "  TEXT to CurrencyCode (walkingkooka.convert.ConverterTextToCurrencyCode)\n" +
                "  TEXT to CurrencyCodeSet (walkingkooka.convert.ConverterTextToCollectionSetCurrencyCodeSet)\n" +
                "  TEXT to CurrencyValue (walkingkooka.convert.ConverterTextToCurrencyValue)\n" +
                "  TEXT to Indentation (walkingkooka.convert.ConverterTextToIndentation)\n" +
                "  TEXT to LineEnding (walkingkooka.convert.ConverterTextToLineEnding)\n" +
                "  TEXT to LocalDateList (walkingkooka.convert.ConverterTextToCollectionListLocalDateList)\n" +
                "  TEXT to LocalDateTimeList (walkingkooka.convert.ConverterTextToCollectionListLocalDateTimeList)\n" +
                "  TEXT to LocalTimeList (walkingkooka.convert.ConverterTextToCollectionListLocalTimeList)\n" +
                "  TEXT to Locale (walkingkooka.convert.ConverterTextToLocale)\n" +
                "  TEXT to LocaleLanguageTag (walkingkooka.convert.ConverterTextToLocaleLanguageTag)\n" +
                "  TEXT to LocaleLanguageTagSet (walkingkooka.convert.ConverterTextToCollectionSetLocaleLanguageTagSet)\n" +
                "  TEXT to LoggingLevel (walkingkooka.convert.ConverterTextToLoggingLevel)\n" +
                "  TEXT to NumberList (walkingkooka.convert.ConverterTextToCollectionListNumberList)\n" +
                "  TEXT to Path (walkingkooka.convert.ConverterTextToPath)\n" +
                "  TEXT to Properties (walkingkooka.convert.ConverterTextToProperties)\n" +
                "  TEXT to StringList (walkingkooka.convert.ConverterTextToCollectionListStringList)\n" +
                "  TEXT to TsvStringList (walkingkooka.convert.ConverterTextToCollectionListTsvStringList)\n" +
                "  TEXT to TsvStringSet (walkingkooka.convert.ConverterTextToCollectionSetTsvStringSet)\n" +
                "  TEXT to ZoneOffset (walkingkooka.convert.ConverterTextToZoneOffset)\n" +
                "  if type (walkingkooka.convert.ConverterSimple)\n" +
                "  never (walkingkooka.convert.ConverterNever)\n" +
                "  to Binary (walkingkooka.convert.ConverterToBinary)\n" +
                "  to CsvStringList (walkingkooka.convert.ConverterToCsvStringList)\n" +
                "  to Currency (walkingkooka.convert.ConverterToCurrency)\n" +
                "  to CurrencyCode (walkingkooka.convert.ConverterToCurrencyCode)\n" +
                "  to DateTimeSymbols (walkingkooka.convert.ConverterLocaleToDateTimeSymbols)\n" +
                "  to DecimalNumberSymbols (walkingkooka.convert.ConverterLocaleToDecimalNumberSymbols)\n" +
                "  to Locale (walkingkooka.convert.ConverterLocaleToLocale)\n" +
                "  to LocaleLanguageTag (walkingkooka.convert.ConverterLocaleToLocaleLanguageTag)\n" +
                "  to MultiLineText (walkingkooka.convert.ConverterToMultiLineText)\n" +
                "  to Properties (walkingkooka.convert.ConverterToProperties)\n" +
                "  to TsvStringList (walkingkooka.convert.ConverterToTsvStringList)\n" +
                "  toDateTimeSymbols (walkingkooka.convert.ConverterToDateTimeSymbols)\n" +
                "  toDecimalNumberSymbols (walkingkooka.convert.ConverterToDecimalNumberSymbols)\n" +
                "  toText (walkingkooka.convert.ConverterToText)\n"
        );
    }

    @Test
    public void testPublicStaticMethodsWithoutMathContextParameter() {
        this.publicStaticMethodParametersTypeCheck(MathContext.class);
    }

    @Override
    public Class<Converters> type() {
        return Converters.class;
    }

    @Override
    public boolean canHavePublicTypes(final Method method) {
        return false;
    }
}
