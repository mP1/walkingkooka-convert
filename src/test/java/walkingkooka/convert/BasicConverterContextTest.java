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
import walkingkooka.datetime.DateTimeContext;
import walkingkooka.datetime.DateTimeContexts;
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.math.DecimalNumberContextDelegator;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.math.DecimalNumberSymbols;
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;

import java.math.MathContext;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class BasicConverterContextTest implements ClassTesting2<BasicConverterContext>,
    ConverterContextTesting<BasicConverterContext>,
    DecimalNumberContextDelegator {

    private final static long NUMBER_TO_DATE_OFFSET = 0;

    private final static Converter<ConverterContext> CONVERTER = Converters.objectToString();

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

    private final static Locale LOCALE = Locale.ENGLISH;
    private final static MathContext MATH_CONTEXT = MathContext.DECIMAL32;

    @Test
    public void testWithNullConverterFails() {
        assertThrows(
            NullPointerException.class,
            () -> BasicConverterContext.with(
                NUMBER_TO_DATE_OFFSET,
                null,
                this.dateTimeContext(),
                this.decimalNumberContext()
            )
        );
    }

    @Test
    public void testWithNullDateTimeContextFails() {
        assertThrows(
            NullPointerException.class,
            () -> BasicConverterContext.with(
                NUMBER_TO_DATE_OFFSET,
                CONVERTER,
                null,
                this.decimalNumberContext())
        );
    }

    @Test
    public void testWithNullDecimalNumberContextFails() {
        assertThrows(
            NullPointerException.class,
            () -> BasicConverterContext.with(
                NUMBER_TO_DATE_OFFSET,
                CONVERTER,
                this.dateTimeContext(),
                null)
        );
    }

    // convert..........................................................................................................

    @Test
    public void testConvert() {
        this.convertAndCheck(new StringBuilder("123"), String.class, "123");
    }

    @Test
    public void testLocale() {
        this.localeAndCheck(
            this.createContext(),
            LOCALE
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createContext(),
            this.dateTimeContext() + " " + decimalNumberContext());
    }

    @Override
    public BasicConverterContext createContext() {
        return BasicConverterContext.with(
            NUMBER_TO_DATE_OFFSET,
            CONVERTER,
            this.dateTimeContext(),
            decimalNumberContext()
        );
    }

    private DateTimeContext dateTimeContext() {
        final Locale locale = Locale.FRANCE;

        return DateTimeContexts.basic(
            DateTimeSymbols.fromDateFormatSymbols(
                new DateFormatSymbols(locale)
            ),
            locale,
            1900,
            20,
            LocalDateTime::now
        );
    }

    @Override
    public DecimalNumberContext decimalNumberContext() {
        return DecimalNumberContexts.basic(
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
    }

    @Override
    public MathContext mathContext() {
        return MATH_CONTEXT;
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
