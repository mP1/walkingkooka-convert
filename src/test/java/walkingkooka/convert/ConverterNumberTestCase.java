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

import static org.junit.jupiter.api.Assertions.assertSame;

public abstract class ConverterNumberTestCase<C extends ConverterNumber<T, ConverterContext>, T> extends ConverterTestCase2<C> {

    ConverterNumberTestCase() {
        super();
    }

    @Test
    public void testConvertDoubleNanFails() {
        this.convertFails2(Double.NaN);
    }

    @Test
    public void testConvertDoublePositiveInfinityFails() {
        this.convertFails2(Double.POSITIVE_INFINITY);
    }

    @Test
    public void testConvertDoubleNegativeInfinityFails() {
        this.convertFails2(Double.NEGATIVE_INFINITY);
    }

    @Test
    public void testConvertDoubleMaxFails() {
        this.convertFails2(Double.MAX_VALUE);
    }

    @Test
    public void testConvertDoubleMinFails() {
        this.convertFails2(Double.MIN_VALUE);
    }

    @Test
    public final void testConvertNull() {
        this.convertAndCheck(
            null,
            this.targetType()
        );
    }

    @Override
    public void convertAndCheck(final Object value) {
        assertSame(value, this.convertAndCheck(value, this.targetType(), this.targetType().cast(value)));
    }

    final void convertFails2(final Object value) {
        this.convertFails(value, this.targetType());
    }

    abstract Class<T> targetType();

    // TypeNameTesting..................................................................................................

    @Override
    public final String typeNamePrefix() {
        return Converter.class.getSimpleName() + Number.class.getSimpleName();
    }

    @Override
    public final String typeNameSuffix() {
        return this.targetType().getSimpleName();
    }
}
