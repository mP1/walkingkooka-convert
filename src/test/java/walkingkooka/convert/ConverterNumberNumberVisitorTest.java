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
import walkingkooka.math.NumberVisitor;
import walkingkooka.math.NumberVisitorTesting;
import walkingkooka.reflect.JavaVisibility;
import walkingkooka.text.CharSequences;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class ConverterNumberNumberVisitorTest implements NumberVisitorTesting<ConverterNumberNumberVisitor<Boolean>> {

    @Test
    public void testConvertUnsupported() {
        final Number number = new Number() {
            @Override
            public int intValue() {
                throw new UnsupportedOperationException();
            }

            @Override
            public long longValue() {
                throw new UnsupportedOperationException();
            }

            @Override
            public float floatValue() {
                throw new UnsupportedOperationException();
            }

            @Override
            public double doubleValue() {
                throw new UnsupportedOperationException();
            }

            private final static long serialVersionUID = 1L;
        };
        //noinspection unchecked
        assertEquals(Either.right("Failed to convert " + number + " (" + number.getClass().getName() + ") to " + Boolean.class.getName()),
                ConverterNumberNumberVisitor.convert(this.converter(),
                        number,
                        Boolean.class));
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createVisitor(), this.converter() + " " + CharSequences.quoteAndEscape(Boolean.class.getName()));
    }

    @Override
    public ConverterNumberNumberVisitor<Boolean> createVisitor() {
        //noinspection unchecked
        return new ConverterNumberNumberVisitor<Boolean>(this.converter(), Boolean.class);
    }

    private ConverterNumber<Boolean, ConverterContext> converter() {
        return ConverterNumberBoolean.instance();
    }

    @Override
    public String typeNamePrefix() {
        return ConverterNumber.class.getSimpleName();
    }

    @Override
    public String typeNameSuffix() {
        return NumberVisitor.class.getSimpleName();
    }

    @Override
    public Class<ConverterNumberNumberVisitor<Boolean>> type() {
        return Cast.to(ConverterNumberNumberVisitor.class);
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}
