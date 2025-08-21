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
import walkingkooka.Either;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class TryingShortCircuitingConverterTest implements ClassTesting<TryingShortCircuitingConverter<?>> {

    private final static ConverterContext CONTEXT = new FakeConverterContext() {

        @Override
        public boolean canConvert(final Object value,
                                  final Class<?> type) {
            return true;
        }

        @Override
        public <T> Either<T, String> convert(final Object value,
                                             final Class<T> target) {
            return this.successfulConversion(
                value,
                target
            );
        }
    };

    @Test
    public void testConvertThrowsErrorUncaught() {
        assertThrows(
            Error.class,
            () -> new TextToTryingShortCircuitingConverter<>() {
                @Override
                public boolean isTargetType(final Object value,
                                            final Class<?> type,
                                            final ConverterContext context) {
                    return true;
                }

                @Override
                public Object parseText(final String value,
                                        final Class<?> type,
                                        final ConverterContext context) {
                    throw new Error("Thrown!");
                }
            }.convert(
                "Hello",
                String.class,
                CONTEXT
            )
        );
    }

    @Test
    public void testConvertThrowsUnsupportedOperationExceptionUncaught() {
        final UnsupportedOperationException throwing = new UnsupportedOperationException("Thrown!");

        final UnsupportedOperationException thrown = assertThrows(
            UnsupportedOperationException.class,
            () -> new TextToTryingShortCircuitingConverter<>() {
                @Override
                public boolean isTargetType(final Object value,
                                            final Class<?> type,
                                            final ConverterContext context) {
                    return true;
                }

                @Override
                public Object parseText(final String value,
                                        final Class<?> type,
                                        final ConverterContext context) {
                    throw throwing;
                }
            }.convert(
                "Hello",
                String.class,
                CONTEXT
            )
        );

        assertSame(
            throwing,
            thrown
        );
    }

    // class............................................................................................................

    @Override
    public Class<TryingShortCircuitingConverter<?>> type() {
        return Cast.to(TryingShortCircuitingConverter.class);
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }
}
