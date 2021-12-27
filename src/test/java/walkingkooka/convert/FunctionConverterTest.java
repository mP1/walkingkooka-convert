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

import java.util.function.Function;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class FunctionConverterTest extends ConverterTestCase2<FunctionConverter<String, Boolean, ConverterContext>> {

    private final static Predicate<Object> SOURCE = (t) -> t instanceof String;
    private final static Predicate<Class<?>> TARGET = Predicate.isEqual(Boolean.class);
    private final static Function<String, Boolean> CONVERTER = Boolean::valueOf;

    @Test
    public void testWithNullSourceTypeFails() {
        assertThrows(NullPointerException.class, () -> FunctionConverter.with(null, TARGET, CONVERTER));
    }

    @Test
    public void testWithNullTargetTypeFails() {
        assertThrows(NullPointerException.class, () -> FunctionConverter.with(SOURCE, null, CONVERTER));
    }

    @Test
    public void testWithNullConverterFunctionFails() {
        assertThrows(NullPointerException.class, () -> FunctionConverter.with(null, TARGET, null));
    }

    // converter........................................................................................................

    @Test
    public void testNull() {
        this.convertAndCheck2(null, false);
    }

    @Test
    public void testTrue() {
        this.convertAndCheck2(Boolean.TRUE.toString(), true);
    }

    @Test
    public void testFalse() {
        this.convertAndCheck2(Boolean.FALSE.toString(), false);
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createConverter().setToString("String->Boolean"), "String->Boolean");
    }

    // Converter........................................................................................................

    @Override
    public FunctionConverter<String, Boolean, ConverterContext> createConverter() {
        return FunctionConverter.with(SOURCE, TARGET, FunctionConverterTest::stringToBoolean);
    }

    private static Boolean stringToBoolean(final String s) {
        return Boolean.valueOf(s);
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    private void convertAndCheck2(final String value,
                                  final boolean expected) {
        this.convertAndCheck(value, Boolean.class, expected);
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<FunctionConverter<String, Boolean, ConverterContext>> type() {
        return Cast.to(FunctionConverter.class);
    }
}
