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
import org.opentest4j.AssertionFailedError;
import walkingkooka.Cast;
import walkingkooka.Either;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class CanConvertTestingTest {

    final Object VALUE = 123;
    final Class<String> TARGET = String.class;
    final String CONVERTED = "*123*";

    @Test
    public void testConvertFailsFails() {

        this.create(true, Either.right("failed message!"))
                .convertFails(VALUE, TARGET);
    }

    @Test
    public void testConvertFailsPass() {
        boolean pass = false;
        try {
            this.create(true, Either.left(CONVERTED))
                    .convertFails(VALUE, TARGET);
            pass = true;
        } catch (final AssertionFailedError expected) {
        }

        assertEquals(false, pass);
    }

    @Test
    public void testConvert() {
        this.create(true, Either.left(CONVERTED))
                .convertAndCheck(VALUE, TARGET, CONVERTED);
    }

    @Test
    public void testConvertFails() {
        boolean pass = false;
        try {
            this.create(true, Either.right("Failed message 123"))
                    .convertAndCheck(VALUE, TARGET, CONVERTED);
            pass = true;
        } catch (final AssertionFailedError expected) {
        }

        assertEquals(false, pass);
    }

    @Test
    public void testConvertOrFailPass() {
        this.create(true, Either.left(CONVERTED))
                .convertOrFailAndCheck(VALUE, TARGET, CONVERTED);
    }

    @Test
    public void testConvertOrFailFails() {
        assertThrows(ConversionException.class, () ->
                this.create(true, Either.right("Failed!"))
                        .convertOrFailAndCheck(VALUE, TARGET, CONVERTED));
    }

    @Test
    public void testConvertOrFailCustomConvertThrowableFails() {
        final String message = "message 123";
        final RuntimeException thrown = assertThrows(RuntimeException.class,
                () -> {
                    new CanConvert() {

                        @Override
                        public boolean canConvert(final Object value,
                                                  final Class<?> type) {
                            return false;
                        }

                        @Override //
                        public <T> Either<T, String> convert(final Object value,
                                                             final Class<T> target) {
                            return Either.right(message);
                        }

                        @Override
                        public RuntimeException convertThrowable(final String message) {
                            return new RuntimeException(message);
                        }
                    }.convertOrFail(this, this.getClass());
                });

        assertEquals(message, thrown.getMessage(), "message");
    }

    @Test
    public void testConvertOrFailDoesntThrows() {
        this.create(true, Either.left(CONVERTED));
    }

    private <T> CanConvertTesting<CanConvert> create(final boolean can,
                                                     final Either<T, String> result) {
        return new CanConvertTesting<CanConvert>() {

            @Override
            public CanConvert createCanConvert() {
                return new CanConvert() {

                    @Override
                    public boolean canConvert(final Object value,
                                              final Class<?> target) {
                        assertEquals(VALUE, value, "value");
                        assertEquals(TARGET, target, "target");
                        return can;
                    }

                    @Override
                    public <T> Either<T, String> convert(final Object value,
                                                         final Class<T> target) {
                        assertEquals(VALUE, value, "value");
                        assertEquals(TARGET, target, "target");
                        return Cast.to(result);
                    }
                };
            }
        };
    }
}
