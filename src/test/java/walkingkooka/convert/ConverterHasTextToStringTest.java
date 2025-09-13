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
import walkingkooka.text.HasText;

public final class ConverterHasTextToStringTest extends ConverterTestCase2<ConverterHasTextToString<ConverterContext>> {

    @Test
    public void testConvertNullToString() {
        this.convertAndCheck(
            null,
            String.class
        );
    }

    @Test
    public void testConvertHasTextToString() {
        final String text = "abc123";

        this.convertAndCheck(
            new HasText() {
                @Override
                public String text() {
                    return text;
                }
            },
            text
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "HasText to String"
        );
    }

    @Override
    public ConverterHasTextToString<ConverterContext> createConverter() {
        return ConverterHasTextToString.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    // class............................................................................................................

    @Override
    public Class<ConverterHasTextToString<ConverterContext>> type() {
        return Cast.to(ConverterHasTextToString.class);
    }

    @Override
    public String typeNamePrefix() {
        return Converter.class.getSimpleName();
    }
}
