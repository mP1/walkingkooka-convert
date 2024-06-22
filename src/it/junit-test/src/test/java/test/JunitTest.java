/*
 * Copyright © 2020 Miroslav Pokorny
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
 */
package test;


import com.google.j2cl.junit.apt.J2clTestInput;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Test;
import walkingkooka.Either;
import walkingkooka.convert.Converter;
import walkingkooka.convert.ConverterContext;
import walkingkooka.convert.ConverterContexts;
import walkingkooka.convert.Converters;
import walkingkooka.convert.FakeConverterContext;
import walkingkooka.text.CharSequences;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Locale;

@J2clTestInput(JunitTest.class)
public class JunitTest {

    private final static Locale LOCALE = Locale.forLanguageTag("EN-AU");

    @Test
    public void testLocaleDateToLong() {
        final long value = 123;

        this.convertAndCheck(
                Converters.localDateToNumber(Converters.JAVA_EPOCH_OFFSET),
                LocalDate.ofEpochDay(value),
                Long.class,
                Long.valueOf(value)
        );
    }

    @Test
    public void testStringToBigDecimal() {
        this.convertAndCheck(
                Converters.stringNumber((c) -> new DecimalFormat("#.#", DecimalFormatSymbols.getInstance(LOCALE))),
                "1.5",
                BigDecimal.class,
                BigDecimal.valueOf(1.5));
    }

    private <T> void convertAndCheck(final Converter converter,
                                     final Object value,
                                     final Class<T> target,
                                     final T expected) {
        this.convertAndCheck(converter,
                value,
                target,
                new FakeConverterContext() {

                    @Override
                    public String currencySymbol() {
                        return "$";
                    }

                    @Override
                    public char decimalSeparator() {
                        return '.';
                    }

                    @Override
                    public String exponentSymbol() {
                        return "E";
                    }

                    @Override
                    public char groupSeparator() {
                        return ',';
                    }

                    @Override
                    public char negativeSign() {
                        return '-';
                    }

                    @Override
                    public char percentageSymbol() {
                        return '%';
                    }

                    @Override
                    public Locale locale() {
                        return Locale.forLanguageTag("EN-AU");
                    }
                },
                expected);
    }

    private <T> void convertAndCheck(final Converter converter,
                                     final Object value,
                                     final Class<T> target,
                                     final ConverterContext context,
                                     final T expected) {
        Assert.assertEquals(converter + " can convert(" + CharSequences.quoteIfChars(value) + "(" + value.getClass().getName() + ")," + target.getName() + ")",
                true,
                converter.canConvert(value, target, context));

        final Either<T, String> result = converter.convert(value, target, context);
        if (result.isRight()) {
            throw new AssertionFailedError(result.rightValue());
        }

        final T convertedValue = result.leftValue();
        Assert.assertEquals("Failed to convert " + CharSequences.quoteIfChars(value) + " (" + value.getClass().getName() + ")= to " + target.getName(),
                expected,
                convertedValue);
    }
}
