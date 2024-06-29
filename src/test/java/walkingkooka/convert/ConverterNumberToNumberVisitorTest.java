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

public final class ConverterNumberToNumberVisitorTest implements NumberVisitorTesting<ConverterNumberToNumberVisitor<Boolean>> {

    private final static ConverterContext CONTEXT = ConverterContexts.fake();

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
        this.checkEquals(
                Either.right("Failed to convert " + number + " (" + number.getClass().getName() + ") to " + Boolean.class.getName()),
                ConverterNumberToNumberVisitor.convert(
                        this.converter(),
                        number,
                        Boolean.class,
                        ConverterContexts.fake()
                )
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
                this.createVisitor(),
                this.converter() + " " + CharSequences.quoteAndEscape(Boolean.class.getName()) + " " + CONTEXT
        );
    }

    @Override
    public ConverterNumberToNumberVisitor<Boolean> createVisitor() {
        //noinspection unchecked
        return new ConverterNumberToNumberVisitor<>(
                this.converter(),
                Boolean.class,
                CONTEXT
        );
    }

    private ConverterNumber<Boolean, ConverterContext> converter() {
        return ConverterNumberToBoolean.instance();
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
    public Class<ConverterNumberToNumberVisitor<Boolean>> type() {
        return Cast.to(ConverterNumberToNumberVisitor.class);
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}
