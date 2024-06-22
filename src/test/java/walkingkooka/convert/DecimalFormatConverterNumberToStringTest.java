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

public final class DecimalFormatConverterNumberToStringTest extends DecimalFormatConverterTestCase<DecimalFormatConverterNumberToString<ConverterContext>> {

    @Test
    public void testInvalidLocaleFails() {
        this.convertFails(this.createConverter(),
                0,
                String.class,
                this.createContext(Locale.ENGLISH));
    }

    @Test
    public void testNull() {
        this.convertAndCheck(
                null,
                String.class,
                null
        );
    }

    @Test
    public void testByte() {
        this.convertAndCheck2("#.000", Byte.MAX_VALUE, "127.000");
    }

    @Test
    public void testShort() {
        this.convertAndCheck2("#.000", Short.MAX_VALUE, "32767.000");
    }

    @Test
    public void testInteger() {
        this.convertAndCheck2("$ #.000", 123, "$ 123.000");
    }

    @Test
    public void testLong() {
        this.convertAndCheck2("$ #.000", 123L, "$ 123.000");
    }

    @Test
    public void testFloat() {
        this.convertAndCheck2("$ #.000", 123.5f, "$ 123.500");
    }

    @Test
    public void testDouble() {
        this.convertAndCheck2("$ #.000", 123.5, "$ 123.500");
    }

    @Test
    public void testBigDecimal() {
        this.convertAndCheck2("$ #.000", BigDecimal.valueOf(123.5), "$ 123.500");
    }

    @Test
    public void testBigInteger() {
        this.convertAndCheck2("$ #.000", BigInteger.valueOf(123), "$ 123.000");
    }

    @Test
    public void testLocaleChange() {
        final DecimalFormatConverterNumberToString<ConverterContext> converter = this.createConverter("$ ###.00");
        this.convertAndCheck(converter,
                1.25,
                String.class,
                this.createContext(Locale.CANADA),
                "$ 1.25");

        this.convertAndCheck(converter,
                1234.5,
                String.class,
                this.createContext(Locale.GERMANY),
                "$ 1234,50");
    }

    // ConverterTesting..................................................................................................

    @Override
    DecimalFormatConverterNumberToString<ConverterContext> createConverter(final Function<DecimalNumberContext, DecimalFormat> decimalFormat) {
        return DecimalFormatConverterNumberToString.with(decimalFormat);
    }

    @Override
    public Class<DecimalFormatConverterNumberToString<ConverterContext>> type() {
        return Cast.to(DecimalFormatConverterNumberToString.class);
    }

    // TypeNameTesting..................................................................................................

    @Override
    public String typeNameSuffix() {
        return Number.class.getSimpleName() + "To" + String.class.getSimpleName();
    }
}
