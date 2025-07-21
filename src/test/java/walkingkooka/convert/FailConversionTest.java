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
import walkingkooka.Either;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

public final class FailConversionTest implements ClassTesting<FailConversion> {
    @Test
    public void testHandle() {
        this.checkEquals(
            Either.right("Failed to convert 1 (java.lang.Integer) to java.lang.String"),
            FailConversion.handle(1, String.class)
        );
    }

    @Test
    public void testHandleNullValue() {
        this.checkEquals(
            Either.right("Failed to convert null to java.lang.String, java.lang.NullPointerException"),
            FailConversion.handle(null, String.class, new NullPointerException()
            )
        );
    }

    @Test
    public void testHandleNullMessage() {
        this.checkEquals(
            Either.right("Failed to convert 1 (java.lang.Integer) to java.lang.String, java.lang.NullPointerException"),
            FailConversion.handle(1, String.class, new NullPointerException())
        );
    }

    @Test
    public void testHandleEmptyMessage() {
        this.checkEquals(
            Either.right("Failed to convert 1 (java.lang.Integer) to java.lang.String, java.lang.NullPointerException"),
            FailConversion.handle(1, String.class, new NullPointerException(""))
        );
    }

    @Test
    public void testHandleMessage() {
        this.checkEquals(
            Either.right("Failed to convert 1 (java.lang.Integer) to java.lang.String, Exception message"),
            FailConversion.handle(1, String.class, new NullPointerException("Exception message"))
        );
    }

    // ClassTesting.....................................................................................................

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }

    @Override
    public Class<FailConversion> type() {
        return FailConversion.class;
    }
}
