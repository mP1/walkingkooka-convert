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
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ConvertOrFailFunctionTestingTest implements ClassTesting<ConvertOrFailFunctionTesting> {

    @Test
    public void testConvertOrFailPass() {
        new TestConvertOrFailFunctionTesting()
                .convertOrFailAndCheck(new ConvertOrFailFunction() {
                    @Override
                    public <T> T convertOrFail(final Object value, final Class<T> target) {
                        return target.cast(Integer.parseInt((String) value));
                    }
                }, "123", Integer.class, 123);
    }

    @Test
    public void testConvertOrFailFails() {
        assertThrows(ConversionException.class, () ->
                new TestConvertOrFailFunctionTesting()
                        .convertOrFailAndCheck(new ConvertOrFailFunction() {
                            @Override
                            public <T> T convertOrFail(final Object value, final Class<T> target) {
                                throw new ConversionException("Fail!");
                            }
                        }, "123", Integer.class, 123));
    }

    @Test
    public void testConvertOrFailThrows() {
        new TestConvertOrFailFunctionTesting()
                .convertOrFailFails(new ConvertOrFailFunction() {
                    @Override
                    public <T> T convertOrFail(final Object value, final Class<T> target) {
                        throw new ConversionException("Fail!");
                    }
                }, "123", Integer.class);
    }

    @Test
    public void testConvertOrFailDoesntThrows() {
        boolean pass = false;
        try {
            new TestConvertOrFailFunctionTesting()
                    .convertOrFailFails(new ConvertOrFailFunction() {
                        @Override
                        public <T> T convertOrFail(final Object value, final Class<T> target) {
                            return target.cast(123);
                        }
                    }, "123", Integer.class);
            pass = true;
        } catch (final AssertionFailedError expected) {
        }
        assertEquals(false, pass);
    }

    final static class TestConvertOrFailFunctionTesting implements ConvertOrFailFunctionTesting {
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PUBLIC;
    }

    @Override
    public Class<ConvertOrFailFunctionTesting> type() {
        return ConvertOrFailFunctionTesting.class;
    }
}
