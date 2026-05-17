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
import walkingkooka.currency.CurrencyCode;
import walkingkooka.currency.CurrencyLocaleContext;
import walkingkooka.currency.FakeCurrencyContext;
import walkingkooka.datetime.DateTimeContext;
import walkingkooka.datetime.DateTimeContexts;
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.locale.LocaleContexts;
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.math.DecimalNumberContextDelegator;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.math.DecimalNumberSymbols;
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;

import java.math.MathContext;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class BasicConverterContextTest implements ClassTesting2<BasicConverterContext>,
    ConverterContextTesting<BasicConverterContext>,
    DecimalNumberContextDelegator {

    private final static boolean CAN_NUMBERS_HAVE_GROUP_SEPARATOR = false;
    private final static long NUMBER_TO_DATE_OFFSET = 0;
    private final static char VALUE_SEPARATOR = ',';

    private final static Converter<ConverterContext> CONVERTER = Converters.objectToString();

    private final static Indentation INDENTATION = Indentation.SPACES2;
    private final static LineEnding LINE_ENDING = LineEnding.NL;
    private final static Locale LOCALE = Locale.ENGLISH;

    private final static CurrencyLocaleContext CURRENCY_LOCALE_CONTEXT = new FakeCurrencyContext() {

        @Override
        public Optional<Currency> currencyForCurrencyCode(final CurrencyCode currencyCode) {
            Objects.requireNonNull(currencyCode, "currencyCode");
            throw new UnsupportedOperationException();
        }

        @Override
        public Optional<Currency> currencyForLocale(final Locale locale) {
            Objects.requireNonNull(locale, "locale");

            return Optional.of(
                Currency.getInstance(locale)
            );
        }

        @Override
        public CurrencyCode currencyCode() {
            return CurrencyCode.parse("XYZ");
        }
    }.setLocaleContext(
        LocaleContexts.jre(LOCALE)
    );

    private final static DateTimeContext DATE_TIME_CONTEXT = DateTimeContexts.basic(
        DateTimeSymbols.fromDateFormatSymbols(
            new DateFormatSymbols(LOCALE)
        ),
        LOCALE,
        1900,
        20,
        LocalDateTime::now
    );

    private final static String CURRENCY = "$$";
    private final static char DECIMAL = ':';
    private final static String EXPONENT = "X";
    private final static char GROUP_SEPARATOR = '/';
    private final static String INFINITY = "Infinity";
    private final static char MINUS = '-';
    private final static char MONETARY_DECIMAL = ';';
    private final static String NAN = "Nan";
    private final static char PERCENT = '!';
    private final static char PERMILL = '^';
    private final static char PLUS = '+';
    private final static char ZERO_DIGIT = '0';

    private final static MathContext MATH_CONTEXT = MathContext.DECIMAL32;

    private final static DecimalNumberContext DECIMAL_NUMBER_CONTEXT = DecimalNumberContexts.basic(
        DecimalNumberContext.DEFAULT_NUMBER_DIGIT_COUNT,
        DecimalNumberSymbols.with(
            MINUS,
            PLUS,
            ZERO_DIGIT,
            CURRENCY,
            DECIMAL,
            EXPONENT,
            GROUP_SEPARATOR,
            INFINITY,
            MONETARY_DECIMAL,
            NAN,
            PERCENT,
            PERMILL
        ),
        LOCALE,
        MATH_CONTEXT
    );

    private final static BinaryNumberConverterFunction<ConverterContext> MULTIPLIER = BinaryNumberConverterFunctions.multiply();

    @Test
    public void testWithNullIndentationFails() {
        assertThrows(
            NullPointerException.class,
            () -> BasicConverterContext.with(
                CAN_NUMBERS_HAVE_GROUP_SEPARATOR,
                NUMBER_TO_DATE_OFFSET,
                null,
                LINE_ENDING,
                VALUE_SEPARATOR,
                CONVERTER,
                MULTIPLIER,
                CURRENCY_LOCALE_CONTEXT,
                DATE_TIME_CONTEXT,
                DECIMAL_NUMBER_CONTEXT
            )
        );
    }

    @Test
    public void testWithNullLineEndingFails() {
        assertThrows(
            NullPointerException.class,
            () -> BasicConverterContext.with(
                CAN_NUMBERS_HAVE_GROUP_SEPARATOR,
                NUMBER_TO_DATE_OFFSET,
                INDENTATION,
                null,
                VALUE_SEPARATOR,
                CONVERTER,
                MULTIPLIER,
                CURRENCY_LOCALE_CONTEXT,
                DATE_TIME_CONTEXT,
                DECIMAL_NUMBER_CONTEXT
            )
        );
    }

    @Test
    public void testWithNullConverterFails() {
        assertThrows(
            NullPointerException.class,
            () -> BasicConverterContext.with(
                CAN_NUMBERS_HAVE_GROUP_SEPARATOR,
                NUMBER_TO_DATE_OFFSET,
                INDENTATION,
                LINE_ENDING,
                VALUE_SEPARATOR,
                null,
                MULTIPLIER,
                CURRENCY_LOCALE_CONTEXT,
                DATE_TIME_CONTEXT,
                DECIMAL_NUMBER_CONTEXT
            )
        );
    }

    @Test
    public void testWithNullMultiplierFails() {
        assertThrows(
            NullPointerException.class,
            () -> BasicConverterContext.with(
                CAN_NUMBERS_HAVE_GROUP_SEPARATOR,
                NUMBER_TO_DATE_OFFSET,
                INDENTATION,
                LINE_ENDING,
                VALUE_SEPARATOR,
                CONVERTER,
                null,
                CURRENCY_LOCALE_CONTEXT,
                DATE_TIME_CONTEXT,
                DECIMAL_NUMBER_CONTEXT
            )
        );
    }

    @Test
    public void testWithNullCurrencyLocaleContextFails() {
        assertThrows(
            NullPointerException.class,
            () -> BasicConverterContext.with(
                CAN_NUMBERS_HAVE_GROUP_SEPARATOR,
                NUMBER_TO_DATE_OFFSET,
                INDENTATION,
                LINE_ENDING,
                VALUE_SEPARATOR,
                CONVERTER,
                MULTIPLIER,
                null,
                DATE_TIME_CONTEXT,
                DECIMAL_NUMBER_CONTEXT
            )
        );
    }


    @Test
    public void testWithNullDateTimeContextFails() {
        assertThrows(
            NullPointerException.class,
            () -> BasicConverterContext.with(
                CAN_NUMBERS_HAVE_GROUP_SEPARATOR,
                NUMBER_TO_DATE_OFFSET,
                INDENTATION,
                LINE_ENDING,
                VALUE_SEPARATOR,
                CONVERTER,
                MULTIPLIER,
                CURRENCY_LOCALE_CONTEXT,
                null,
                DECIMAL_NUMBER_CONTEXT
            )
        );
    }

    @Test
    public void testWithNullDecimalNumberContextFails() {
        assertThrows(
            NullPointerException.class,
            () -> BasicConverterContext.with(
                CAN_NUMBERS_HAVE_GROUP_SEPARATOR,
                NUMBER_TO_DATE_OFFSET,
                INDENTATION,
                LINE_ENDING,
                VALUE_SEPARATOR,
                CONVERTER,
                MULTIPLIER,
                CURRENCY_LOCALE_CONTEXT,
                DATE_TIME_CONTEXT,
                null
            )
        );
    }

    // convert..........................................................................................................

    @Test
    public void testConvert() {
        this.convertAndCheck(
            new StringBuilder("123"),
            String.class,
            "123"
        );
    }

    @Test
    public void testCurrencyCode() {
        this.currencyCodeAndCheck(
            this.createContext(),
            CURRENCY_LOCALE_CONTEXT.currencyCode()
        );
    }

    @Test
    public void testLocale() {
        this.localeAndCheck(
            this.createContext(),
            LOCALE
        );
    }

    @Override
    public BasicConverterContext createContext() {
        return BasicConverterContext.with(
            CAN_NUMBERS_HAVE_GROUP_SEPARATOR,
            NUMBER_TO_DATE_OFFSET,
            INDENTATION,
            LINE_ENDING,
            VALUE_SEPARATOR,
            CONVERTER,
            MULTIPLIER,
            CURRENCY_LOCALE_CONTEXT,
            DATE_TIME_CONTEXT,
            decimalNumberContext()
        );
    }

    @Override
    public int decimalNumberDigitCount() {
        return DecimalNumberContext.DEFAULT_NUMBER_DIGIT_COUNT;
    }

    @Override
    public DecimalNumberContext decimalNumberContext() {
        return DECIMAL_NUMBER_CONTEXT;
    }

    @Override
    public MathContext mathContext() {
        return MATH_CONTEXT;
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createContext(),
            DATE_TIME_CONTEXT + " " + decimalNumberContext()
        );
    }

    // class............................................................................................................

    @Override
    public Class<BasicConverterContext> type() {
        return BasicConverterContext.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}
