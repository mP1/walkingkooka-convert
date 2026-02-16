/*
 * Copyright 2025 Miroslav Pokorny (github.com/mP1)
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

import java.util.Locale;

public final class ConverterLocaleToStringTest extends ConverterTestCase2<ConverterLocaleToString<ConverterContext>> {

    @Test
    public void testConvertLocaleToLocaleFails() {
        this.convertFails(
            Locale.FRANCE,
            Locale.class
        );
    }

    @Test
    public void testConvertLocaleToString() {
        final Locale locale = Locale.FRANCE;

        this.convertAndCheck(
            locale,
            locale.toLanguageTag()
        );
    }

    @Override
    public ConverterLocaleToString<ConverterContext> createConverter() {
        return ConverterLocaleToString.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            ConverterLocaleToString.instance(),
            "Locale to String"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterLocaleToString<ConverterContext>> type() {
        return Cast.to(ConverterLocaleToString.class);
    }
}
