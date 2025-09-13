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
import walkingkooka.math.DecimalNumberContext;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.function.Function;

public final class ConverterDecimalFormatStringToNumberTest extends ConverterDecimalFormatTestCase<ConverterDecimalFormatStringToNumber<ConverterContext>> {

    @Test
    public void testInvalidLocaleFails() {
        this.convertFails(this.createConverter(),
            "0",
            Number.class,
            this.createContext(Locale.ENGLISH));
    }

    @Test
    public void testConvertNull() {
        this.convertAndCheck(
            null,
            Number.class
        );
    }

    @Test
    public void testConvertByte() {
        this.convertAndCheck3(Byte.MAX_VALUE);
    }

    @Test
    public void testConvertShort() {
        this.convertAndCheck3(Short.MAX_VALUE);
    }

    @Test
    public void testConvertInteger() {
        this.convertAndCheck3(Integer.MAX_VALUE);
    }

    @Test
    public void testConvertLong() {
        this.convertAndCheck3(Long.MAX_VALUE);
    }

    @Test
    public void testConvertFloat() {
        this.convertAndCheck3(1.5f);
    }

    @Test
    public void testConvertDouble() {
        this.convertAndCheck3(1.5);
    }

    @Test
    public void testConvertBigDecimal() {
        this.convertAndCheck3(BigDecimal.valueOf(123.5));
    }

    @Test
    public void testConvertBigInteger() {
        this.convertAndCheck3(BigInteger.valueOf(123));
    }

    private void convertAndCheck3(final Number number) {
        this.convertAndCheck2("#", number.toString(), number);
    }

    @Test
    public void testConvertNumber() {
        final String text = "1234567890123456789012345678901234567890.5";
        this.convertAndCheck(text,
            Number.class,
            new BigDecimal(text));
    }

    @Test
    public void testConvertNumber2() {
        this.convertAndCheck("123.5",
            Number.class,
            new BigDecimal("123.5"));
    }

    @Test
    public void testConvertCurrency() {
        this.convertAndCheck2(
            "$ #",
            "$ 123.5",
            BigDecimal.class,
            Locale.UK,
            BigDecimal.valueOf(123.5)
        );
    }

    @Test
    public void testConvertPercentage() {
        this.convertAndCheck2(
            "#%",
            "123.5%",
            BigDecimal.class,
            BigDecimal.valueOf(1.235)
        );
    }

    // ConverterTesting..................................................................................................

    @Override
    ConverterDecimalFormatStringToNumber<ConverterContext> createConverter(final Function<DecimalNumberContext, DecimalFormat> decimalFormat) {
        return ConverterDecimalFormatStringToNumber.with(decimalFormat);
    }

    @Override
    public Class<ConverterDecimalFormatStringToNumber<ConverterContext>> type() {
        return Cast.to(ConverterDecimalFormatStringToNumber.class);
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "DecimalFormat String to Number"
        );
    }
}
