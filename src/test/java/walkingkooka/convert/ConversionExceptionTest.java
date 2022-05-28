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
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.reflect.StandardThrowableTesting;

public final class ConversionExceptionTest implements StandardThrowableTesting<ConversionException> {

    private final static Object VALUE = 123;

    private final static Class<?> TYPE = Void.class;

    @Test
    public void testNew() {
        final ConversionException exception =  new ConversionException(
                MESSAGE,
                VALUE,
                TYPE
        );

        this.checkMessage(exception, MESSAGE);
        this.checkEquals(VALUE, exception.value(), "value");
        this.checkEquals(TYPE, exception.type(), "type");
        this.checkCause(exception, null);
    }

    @Test
    public void testNewWithCause() {
        final Throwable cause = new Throwable();

        final ConversionException exception =  new ConversionException(
                MESSAGE,
                VALUE,
                TYPE,
                cause
        );

        this.checkMessage(exception, MESSAGE);
        this.checkEquals(VALUE, exception.value(), "value");
        this.checkEquals(TYPE, exception.type(), "type");
        this.checkCause(exception, cause);
    }

    @Override
    public ConversionException createThrowable(final String message) {
        return new ConversionException(
                message,
                VALUE,
                TYPE
        );
    }

    @Override
    public ConversionException createThrowable(final String message, final Throwable cause) {
        return new ConversionException(
                message,
                VALUE,
                TYPE,
                cause
        );
    }
    
    @Override
    public Class<ConversionException> type() {
        return ConversionException.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
