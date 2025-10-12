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

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ConverterTestingTest implements ConverterTesting {

    private final static String VALUE = "xyz";
    private final static Integer EXPECTED = 123;
    private final static ConverterContext CONTEXT = ConverterContexts.fake();

    @Test
    public void testConvertAndCheckNullValue() {
        this.convertAndCheck(
            new Converter<>() {
                @Override
                public boolean canConvert(final Object v,
                                          final Class<?> t,
                                          final ConverterContext c) {
                    return true;
                }

                @Override
                public <T> Either<T, String> convert(final Object v,
                                                     final Class<T> t,
                                                     final ConverterContext c) {
                    return Either.left(null);
                }
            },
            null,
            Integer.class,
            CONTEXT,
            null);
    }

    @Test
    public void testConvertAndCheck() {
        this.convertAndCheck(
            new TestConverter(),
            VALUE,
            Integer.class,
            CONTEXT,
            EXPECTED);
    }

    @Test
    public void testConvertAndCheckNoMessageFails() {
        assertThrows(
            AssertionError.class,
            () -> this.convertAndCheck(
                new FakeConverter<>() {
                    @Override
                    public boolean canConvert(final Object v,
                                              final Class<?> t,
                                              final ConverterContext c) {
                        return false;
                    }
                },
                VALUE,
                Integer.class,
                CONTEXT,
                EXPECTED
            )
        );
    }

    @Test
    public void testConvertAndCheckNoMessageFails2() {
        assertThrows(
            AssertionError.class,
            () -> this.convertAndCheck(
                new TestConverter(),
                VALUE,
                Integer.class,
                CONTEXT,
                9999
            )
        );
    }

    @Test
    public void testConvertFailsNoMessage() {
        final String value = "xyz";
        final ConverterContext context = ConverterContexts.fake();

        this.convertFails(
            new Converter<>() {
                @Override
                public boolean canConvert(final Object v,
                                          final Class<?> t,
                                          final ConverterContext c) {
                    check(v, t, c);
                    return true;
                }

                @Override
                public <T> Either<T, String> convert(final Object v,
                                                     final Class<T> t,
                                                     final ConverterContext c) {
                    check(v, t, c);
                    return Either.right("Conversion fails!");
                }

                private void check(final Object v,
                                   final Class<?> t,
                                   final ConverterContext c) {
                    checkEquals(value, v, "value");
                    checkEquals(Integer.class, t, "type");
                    checkEquals(context, c, "context");
                }
            },
            value,
            Integer.class,
            context
        );
    }

    @Test
    public void testConvertFailsNoMessage2() {
        final String value = "xyz";
        final ConverterContext context = ConverterContexts.fake();

        assertThrows(
            AssertionError.class,
            () -> this.convertFails(
                new TestConverter(),
                value,
                String.class,
                context
            )
        );
    }

    private class TestConverter implements Converter<ConverterContext> {
        @Override
        public boolean canConvert(final Object v,
                                  final Class<?> t,
                                  final ConverterContext c) {
            check(v, t, c);
            return true;
        }

        @Override
        public <T> Either<T, String> convert(final Object v,
                                             final Class<T> t,
                                             final ConverterContext c) {
            check(v, t, c);
            return Either.left(t.cast(EXPECTED));
        }

        private void check(final Object v,
                           final Class<?> t,
                           final ConverterContext c) {
            checkEquals(VALUE, v, "value");
            checkEquals(Integer.class, t, "type");
            checkEquals(CONTEXT, c, "context");
        }
    }

    @Test
    public void testConvertOrFailSuccess() {
        this.checkEquals(
            1,
            new FakeConverter<>() {
                @Override
                public <T> Either<T, String> convert(final Object value,
                                                     final Class<T> type,
                                                     final ConverterContext context) {
                    return Cast.to(Either.left(1));
                }
            }.convertOrFail(
                this,
                Integer.class,
                ConverterContexts.fake()
            )
        );
    }

    @Test
    public void testConvertOrFailFailed() {
        assertThrows(
            ConverterException.class,
            () -> {
                new FakeConverter<>() {
                    @Override
                    public <T> Either<T, String> convert(final Object value,
                                                         final Class<T> type,
                                                         final ConverterContext context) {
                        return this.failConversion(value, type);
                    }
                }.convertOrFail(
                    this,
                    Boolean.class,
                    ConverterContexts.fake()
                );
            }
        );
    }

    // convertFail with message.........................................................................................

    @Test
    public void testConvertFailWithMessage() {
        this.convertFails(
            new FakeConverter<>() {
                @Override
                public <T> Either<T, String> convert(final Object value,
                                                     final Class<T> type,
                                                     final ConverterContext context) {
                    return this.failConversion(value, type);
                }
            },
            "*VALUE*", // value
            this.getClass(), // target type,
            ConverterContexts.fake(),
            "Failed to convert \"*VALUE*\" (java.lang.String) to walkingkooka.convert.ConverterTestingTest"
        );
    }

    @Test
    public void testConvertFailWithWrongMessage() {
        assertThrows(
            AssertionError.class,
            () -> this.convertFails(
                new FakeConverter<>() {
                    @Override
                    public <T> Either<T, String> convert(final Object value,
                                                         final Class<T> type,
                                                         final ConverterContext context) {
                        return this.failConversion(value, type);
                    }
                },
                "*VALUE*", // value
                this.getClass(), // target type,
                ConverterContexts.fake(),
                "WRONG MESSAGE HERE"
            )
        );
    }

    @Test
    public void testConvertFailWithSupplierMessage() {
        this.convertFails(
            new FakeConverter<>() {
                @Override
                public <T> Either<T, String> convert(final Object value,
                                                     final Class<T> type,
                                                     final ConverterContext context) {
                    return this.failConversion(value, type);
                }
            },
            "*VALUE*", // value
            this.getClass(), // target type,
            ConverterContexts.fake(),
            () -> "Failed to convert \"*VALUE*\" (java.lang.String) to walkingkooka.convert.ConverterTestingTest"
        );
    }

    @Test
    public void testConvertFailWithSupplierWrongMessage() {
        assertThrows(
            AssertionError.class,
            () -> this.convertFails(
                new FakeConverter<>() {
                    @Override
                    public <T> Either<T, String> convert(final Object value,
                                                         final Class<T> type,
                                                         final ConverterContext context) {
                        return this.failConversion(value, type);
                    }
                },
                "*VALUE*", // value
                this.getClass(), // target type,
                ConverterContexts.fake(),
                () -> "WRONG MESSAGE HERE"
            )
        );
    }
}
