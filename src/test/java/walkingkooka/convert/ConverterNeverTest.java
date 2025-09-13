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

public final class ConverterNeverTest extends ConverterTestCase2<ConverterNever<ConverterContext>> {

    @Test
    public void testConvertNull() {
        this.convertFails(
            null,
            Object.class
        );
    }

    @Test
    public void testConvertBooleanTrueToObject() {
        this.convertFails(
            "TRUE",
            Object.class
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "never"
        );
    }

    @Override
    public ConverterNever<ConverterContext> createConverter() {
        return ConverterNever.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    // class............................................................................................................

    @Override
    public Class<ConverterNever<ConverterContext>> type() {
        return Cast.to(ConverterNever.class);
    }
}
