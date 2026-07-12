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
import walkingkooka.datetime.DateTimeSymbols;

import java.text.DateFormatSymbols;
import java.util.Locale;

public final class ConverterHasDateTimeSymbolsTest extends ConverterTestCase2<ConverterHasDateTimeSymbols<ConverterContext>> {

    @Test
    public void testConvertStringToDateTimeSymbolsFails() {
        this.convertFails(
            "",
            DateTimeSymbols.class
        );
    }

    @Test
    public void testConvertNullToDateTimeSymbols() {
        this.convertAndCheck(
            null,
            DateTimeSymbols.class
        );
    }

    @Test
    public void testConvertHasDateTimeSymbolsToDateTimeSymbols() {
        final DateTimeSymbols symbols = DateTimeSymbols.fromDateFormatSymbols(
            new DateFormatSymbols(
                Locale.ENGLISH
            )
        );

        this.convertAndCheck(
            symbols,
            symbols
        );
    }

    @Override
    public ConverterHasDateTimeSymbols<ConverterContext> createConverter() {
        return ConverterHasDateTimeSymbols.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "HasDateTimeSymbols"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterHasDateTimeSymbols<ConverterContext>> type() {
        return Cast.to(ConverterHasDateTimeSymbols.class);
    }
}
