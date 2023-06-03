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
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.reflect.ClassTesting2;
import walkingkooka.reflect.JavaVisibility;

import java.math.MathContext;
import java.time.LocalDateTime;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class BasicConverterContextTest implements ClassTesting2<BasicConverterContext>,
        ConverterContextTesting<BasicConverterContext> {

    private final static Converter<ConverterContext> CONVERTER = Converters.objectString();

    private final static String CURRENCY = "$$";
    private final static char DECIMAL = 'D';
    private final static String EXPONENT = "X";
    private final static char GROUP_SEPARATOR = 'G';
    private final static char MINUS = 'M';
    private final static char PERCENTAGE = 'R';
    private final static char PLUS = 'P';

    private final static Locale LOCALE = Locale.ENGLISH;
    private final static MathContext MATH_CONTEXT = MathContext.DECIMAL32;

    @Test
    public void testWithNullConverterFails() {
        assertThrows(NullPointerException.class, () -> BasicConverterContext.with(null,
                this.dateTimeContext(),
                this.decimalNumberContext()));
    }

    @Test
    public void testWithNullDateTimeContextFails() {
        assertThrows(NullPointerException.class, () -> BasicConverterContext.with(CONVERTER,
                null,
                this.decimalNumberContext()));
    }

    @Test
    public void testWithNullDecimalNumberContextFails() {
        assertThrows(NullPointerException.class, () -> BasicConverterContext.with(CONVERTER,
                this.dateTimeContext(),
                null));
    }

    @Test
    public void testConvert() {
        this.convertAndCheck(new StringBuilder("123"), String.class, "123");
    }

    @Test
    public void testLocale() {
        this.hasLocaleAndCheck(this.createContext(), LOCALE);
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createContext(),
                this.dateTimeContext() + " " + decimalNumberContext());
    }

    @Override
    public BasicConverterContext createContext() {
        return BasicConverterContext.with(CONVERTER, this.dateTimeContext(), decimalNumberContext());
    }

    private DateTimeContext dateTimeContext() {
        return DateTimeContexts.locale(
                Locale.FRANCE,
                1900,
                20,
                LocalDateTime::now
        );
    }

    private DecimalNumberContext decimalNumberContext() {
        return DecimalNumberContexts.basic(CURRENCY,
                DECIMAL,
                EXPONENT,
                GROUP_SEPARATOR,
                MINUS,
                PERCENTAGE,
                PLUS,
                LOCALE,
                MATH_CONTEXT);
    }

    @Override
    public String currencySymbol() {
        return CURRENCY;
    }

    @Override
    public char decimalSeparator() {
        return DECIMAL;
    }

    @Override
    public String exponentSymbol() {
        return EXPONENT;
    }

    @Override
    public char groupSeparator() {
        return GROUP_SEPARATOR;
    }

    @Override
    public MathContext mathContext() {
        return MATH_CONTEXT;
    }

    @Override
    public char negativeSign() {
        return MINUS;
    }

    @Override
    public char percentageSymbol() {
        return PERCENTAGE;
    }

    @Override
    public char positiveSign() {
        return PLUS;
    }

    @Override
    public Class<BasicConverterContext> type() {
        return BasicConverterContext.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}
