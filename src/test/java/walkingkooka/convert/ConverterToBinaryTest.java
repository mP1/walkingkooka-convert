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

import java.nio.charset.StandardCharsets;

public final class ConverterToBinaryTest extends ConverterTestCase2<ConverterToBinary<ConverterContext>> {

    @Test
    public void testConvertStringToBinaryFails() {
        this.convertFails(
            "",
            Binary.class
        );
    }

    @Test
    public void testConvertNullToBinary() {
        this.convertAndCheck(
            null,
            Binary.class
        );
    }

    @Test
    public void testConvertHasBinaryToBinary() {
        final Binary binary = Binary.with(
            "Hello".getBytes(StandardCharsets.UTF_8)
        );

        this.convertAndCheck(
            binary
        );
    }

    @Override
    public ConverterToBinary<ConverterContext> createConverter() {
        return ConverterToBinary.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "toBinary"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterToBinary<ConverterContext>> type() {
        return Cast.to(ConverterToBinary.class);
    }
}
