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
import walkingkooka.HashCodeEqualsDefinedTesting2;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class CustomToStringConverterTest extends ConverterTestCase2<CustomToStringConverter<ConverterContext>>
        implements HashCodeEqualsDefinedTesting2<CustomToStringConverter<ConverterContext>> {

    private final static Converter<ConverterContext> WRAPPED = Converters.objectToString();
    private final static String CUSTOM_TO_STRING = "!!custom-to-string!!";

    @Test
    public void testWrapNullConverterFails() {
        assertThrows(NullPointerException.class, () -> CustomToStringConverter.wrap(null, CUSTOM_TO_STRING));
    }

    @Test
    public void testWrapNullToStringFails() {
        assertThrows(NullPointerException.class, () -> CustomToStringConverter.wrap(WRAPPED, null));
    }

    @Test
    public void testWrapEmptyToStringFails() {
        assertThrows(IllegalArgumentException.class, () -> CustomToStringConverter.wrap(WRAPPED, ""));
    }

    @Test
    public void testWrapWhitespaceToStringFails() {
        assertThrows(IllegalArgumentException.class, () -> CustomToStringConverter.wrap(WRAPPED, " \t"));
    }

    @Test
    public void testDoesntWrapEquivalentToString() {
        assertSame(WRAPPED, CustomToStringConverter.wrap(WRAPPED, WRAPPED.toString()));
    }

    @Test
    public void testUnwrapOtherCustomToStringConverter() {
        final Converter<ConverterContext> first = CustomToStringConverter.wrap(WRAPPED, "different");
        final CustomToStringConverter<ConverterContext> wrapped = Cast.to(CustomToStringConverter.wrap(first, CUSTOM_TO_STRING));
        assertNotSame(first, wrapped);
        assertSame(WRAPPED, wrapped.converter, "wrapped converter");
        assertSame(CUSTOM_TO_STRING, wrapped.toString, "wrapped toString");
    }

    @Test
    public void testConvert() {
        this.convertAndCheck(123, String.class, String.valueOf(123));
    }

    @Test
    public void testEqualsDifferentWrappedConverter() {
        this.checkNotEquals(CustomToStringConverter.wrap(Converters.mapper(t -> t instanceof String, Predicate.isEqual(Boolean.class), (String v) -> Boolean.valueOf(v)), CUSTOM_TO_STRING));
    }

    @Test
    public void testEqualsDifferentCustomToString() {
        this.checkNotEquals(CustomToStringConverter.wrap(WRAPPED, "different"));
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createConverter(), CUSTOM_TO_STRING);
    }

    @Override
    public CustomToStringConverter<ConverterContext> createConverter() {
        return Cast.to(CustomToStringConverter.wrap(WRAPPED, CUSTOM_TO_STRING));
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    @Override
    public Class<CustomToStringConverter<ConverterContext>> type() {
        return Cast.to(CustomToStringConverter.class);
    }

    @Override
    public CustomToStringConverter<ConverterContext> createObject() {
        return this.createConverter();
    }
}
