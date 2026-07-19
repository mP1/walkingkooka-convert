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
import walkingkooka.math.HasDecimalNumberSymbolsTesting;

public final class ConverterToDecimalNumberSymbolsTest extends ConverterTestCase2<ConverterToDecimalNumberSymbols<ConverterContext>>
    implements HasDecimalNumberSymbolsTesting {

    @Test
    public void testConvertStringToDecimalNumberSymbolsFails() {
        this.convertFails(
            "",
            DecimalNumberSymbols.class
        );
    }

    @Test
    public void testConvertNullToDecimalNumberSymbols() {
        this.convertAndCheck(
            null,
            DecimalNumberSymbols.class
        );
    }

    @Test
    public void testConvertHasDecimalNumberSymbolsToDecimalNumberSymbols() {
        this.convertAndCheck(
            DECIMAL_NUMBER_SYMBOLS,
            DECIMAL_NUMBER_SYMBOLS
        );
    }

    @Override
    public ConverterToDecimalNumberSymbols<ConverterContext> createConverter() {
        return ConverterToDecimalNumberSymbols.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "toDecimalNumberSymbols"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterToDecimalNumberSymbols<ConverterContext>> type() {
        return Cast.to(ConverterToDecimalNumberSymbols.class);
    }
}
