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
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.reflect.StandardThrowableTesting;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ConverterExceptionTest implements StandardThrowableTesting<ConverterException> {

    private final static Object VALUE = 123;

    private final static Class<?> TYPE = Void.class;

    @Test
    public void testNew() {
        final ConverterException exception = new ConverterException(
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

        final ConverterException exception = new ConverterException(
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

    // setPrefix........................................................................................................

    @Test
    public void testSetPrefixWithNullFails() {
        assertThrows(
            NullPointerException.class,
            () -> new ConverterException(
                "Message",
                null,
                Void.class
            ).setPrefix(null)
        );
    }

    private final static String MESSAGE = "Message 123";

    @Test
    public void testSetPrefixWithEmpty() {
        final ConverterException exception = new ConverterException(
            MESSAGE,
            1,
            Void.class
        ).setPrefix("");

        this.checkMessage(
            exception,
            MESSAGE
        );
    }

    @Test
    public void testSetPrefixWithNonEmpty() {
        final ConverterException exception = new ConverterException(
            MESSAGE,
            1,
            Void.class
        ).setPrefix("Hello: ");

        this.checkMessage(
            exception,
            "Hello: " + MESSAGE
        );
    }

    @Test
    public void testConverterFailsAndGetMessage() {
        final ConverterException thrown = assertThrows(
            ConverterException.class,
            () -> new FakeConverter<>() {
                @Override
                public <T> Either<T, String> convert(final Object value,
                                                     final Class<T> type,
                                                     final ConverterContext context) {
                    return this.failConversion(
                        value,
                        type
                    );
                }
            }.convertOrFail(
                123,
                String.class,
                ConverterContexts.fake()
            )
        );

        this.checkMessage(
            thrown,
            "Failed to convert 123 (java.lang.Integer) to java.lang.String"
        );
    }

    @Test
    public void testConverterFailsSetPrefixAndGetMessage() {
        final ConverterException thrown = assertThrows(
            ConverterException.class,
            () -> new FakeConverter<>() {
                @Override
                public <T> Either<T, String> convert(final Object value,
                                                     final Class<T> type,
                                                     final ConverterContext context) {
                    return this.failConversion(
                        value,
                        type
                    );
                }
            }.convertOrFail(
                123,
                String.class,
                ConverterContexts.fake()
            )
        );

        this.checkMessage(
            thrown.setPrefix("Hello: "),
            "Hello: Failed to convert 123 (java.lang.Integer) to java.lang.String"
        );
    }

    @Override
    public ConverterException createThrowable(final String message) {
        return new ConverterException(
            message,
            VALUE,
            TYPE
        );
    }

    @Override
    public ConverterException createThrowable(final String message, final Throwable cause) {
        return new ConverterException(
            message,
            VALUE,
            TYPE,
            cause
        );
    }

    @Override
    public Class<ConverterException> type() {
        return ConverterException.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
