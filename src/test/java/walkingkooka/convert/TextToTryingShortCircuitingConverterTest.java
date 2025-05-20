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
import walkingkooka.convert.TextToTryingShortCircuitingConverterTest.TestTextToTryingShortCircuitingConverter;
import walkingkooka.text.HasText;

public final class TextToTryingShortCircuitingConverterTest implements ConverterTesting2<TestTextToTryingShortCircuitingConverter, FakeConverterContext> {

    @Test
    public void testConvertNull() {
        this.convertAndCheck(
                null,
                Integer.class,
                999
        );
    }

    @Test
    public void testConvertNonTextFails() {
        this.convertFails(
                1,
                String.class
        );
    }

    @Test
    public void testConvertCharacterToString() {
        this.convertAndCheck(
                '1',
                1
        );
    }

    @Test
    public void testConvertCharSequenceToString() {
        this.convertAndCheck(
                new StringBuilder("1"),
                1
        );
    }

    @Test
    public void testConvertHasTextToString() {
        this.convertAndCheck(
                new HasText() {
                    @Override
                    public String text() {
                        return "1";
                    }
                },
                1
        );
    }

    @Test
    public void testConvertStringToString() {
        this.convertAndCheck(
                "1",
                1
        );
    }

    @Override
    public void testCheckToStringOverridden() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TestTextToTryingShortCircuitingConverter createConverter() {
        return new TestTextToTryingShortCircuitingConverter();
    }

    @Override
    public FakeConverterContext createContext() {
        return new FakeConverterContext() {

            @Override
            public boolean canConvert(final Object value,
                                      final Class<?> type) {
                return this.converter.canConvert(
                        value,
                        type,
                        this
                );
            }

            @Override
            public <T> Either<T, String> convert(final Object value,
                                                 final Class<T> target) {
                return this.converter.convert(
                        value,
                        target,
                        this
                );
            }

            private final Converter<FakeConverterContext> converter = Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString();
        };
    }

    @Override
    public Class<TestTextToTryingShortCircuitingConverter> type() {
        return TestTextToTryingShortCircuitingConverter.class;
    }

    static class TestTextToTryingShortCircuitingConverter implements TextToTryingShortCircuitingConverter<FakeConverterContext> {

        @Override
        public boolean isTargetType(final Object value,
                                    final Class<?> type,
                                    final FakeConverterContext context) {
            return Integer.class == type;
        }

        @Override
        public Object parseText(final String value,
                                final Class<?> type,
                                final FakeConverterContext context) {
            return null == value ?
                    999 :
                    Integer.parseInt(value);
        }
    }
}
