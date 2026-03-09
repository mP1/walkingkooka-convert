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
import walkingkooka.math.DecimalNumberSymbols;
import walkingkooka.props.Properties;

public final class ConverterPropertiesToDecimalNumberSymbolsTest extends ConverterPropertiesToTestCase<ConverterPropertiesToDecimalNumberSymbols<ConverterContext>, DecimalNumberSymbols> {

    private final static Properties PROPERTIES = Properties.parse(
        "currencySymbol=AUD\n" +
            "decimalSeparator=.\n" +
            "exponentSymbol=E\n" +
            "groupSeparator=,\n" +
            "infinitySymbol=INFINITY\n" +
            "monetaryDecimalSeparator=*\n" +
            "nanSymbol=NAN\n" +
            "negativeSign=-\n" +
            "percentSymbol=%\n" +
            "permillSymbol=^\n" +
            "positiveSign=+\n" +
            "zeroDigit=0"
    );

    @Test
    public void testConvertPropertiesToDecimalNumberSymbols() {
        this.convertAndCheck(
            PROPERTIES,
            DecimalNumberSymbols.fromProperties(PROPERTIES)
        );
    }

    @Test
    public void testConvertStringToDecimalNumberSymbols() {
        this.convertAndCheck(
            PROPERTIES.toString(),
            DecimalNumberSymbols.fromProperties(PROPERTIES)
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "Properties to DecimalNumberSymbols"
        );
    }

    @Override
    public ConverterPropertiesToDecimalNumberSymbols<ConverterContext> createConverter() {
        return ConverterPropertiesToDecimalNumberSymbols.instance();
    }

    @Override
    public Class<ConverterPropertiesToDecimalNumberSymbols<ConverterContext>> type() {
        return Cast.to(ConverterPropertiesToDecimalNumberSymbols.class);
    }
}
