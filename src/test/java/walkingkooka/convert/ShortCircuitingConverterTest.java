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
import walkingkooka.convert.ShortCircuitingConverterTest.TestShortCircuitingConverter;

public final class ShortCircuitingConverterTest implements ConverterTesting2<TestShortCircuitingConverter, FakeConverterContext> {

    @Test
    public void testConvertWhenCanConvertFalseFails() {
        this.convertFails(
            "1",
            Void.class
        );
    }

    @Test
    public void testConvert() {
        this.convertAndCheck(
            "12",
            Integer.class,
            12
        );
    }

    @Test
    public void testConvertWhenDoConvertFails() {
        this.convertFails(
            "Hello",
            Integer.class
        );
    }

    @Override
    public void testCheckToStringOverridden() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TestShortCircuitingConverter createConverter() {
        return new TestShortCircuitingConverter();
    }

    @Override
    public FakeConverterContext createContext() {
        return (FakeConverterContext) ConverterContexts.fake();
    }

    @Override
    public Class<TestShortCircuitingConverter> type() {
        return TestShortCircuitingConverter.class;
    }

    static final class TestShortCircuitingConverter implements ShortCircuitingConverter<FakeConverterContext> {

        @Override
        public boolean canConvert(final Object value,
                                  final Class<?> type,
                                  final FakeConverterContext context) {
            return Integer.class == type;
        }

        @Override
        public <T> Either<T, String> doConvert(final Object value,
                                               final Class<T> type,
                                               final FakeConverterContext context) {
            try {
                return this.successfulConversion(
                    Integer.parseInt(value.toString()),
                    type
                );
            } catch (final NumberFormatException cause) {
                return this.failConversion(
                    value,
                    type,
                    cause
                );
            }
        }
    }
}
