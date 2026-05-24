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
import walkingkooka.Binary;
import walkingkooka.Cast;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class ConverterHasBinaryToStringTest extends ConverterTestCase2<ConverterHasBinaryToString<ConverterContext>> {

    private final static Charset CHARSET = StandardCharsets.UTF_8;

    @Test
    public void testConvertNullToStringFails() {
        this.convertFails(
            null,
            String.class
        );
    }

    @Test
    public void testConvertStringToStringFails() {
        this.convertFails(
            "",
            String.class
        );
    }

    @Test
    public void testConvertBinaryToString() {
        final String text = "Hello World 123";

        this.convertAndCheck(
            Binary.with(
                text.getBytes(CHARSET)
            ),
            text
        );
    }

    @Override
    public ConverterHasBinaryToString<ConverterContext> createConverter() {
        return ConverterHasBinaryToString.instance();
    }

    @Override
    public ConverterContext createContext() {
        return new FakeConverterContext() {
            @Override
            public Charset charset() {
                return CHARSET;
            }
        };
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "HasBinary to String"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterHasBinaryToString<ConverterContext>> type() {
        return Cast.to(ConverterHasBinaryToString.class);
    }
}
