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
import walkingkooka.ToStringTesting;
import walkingkooka.reflect.ClassTesting;
import walkingkooka.reflect.JavaVisibility;

import java.math.BigInteger;

public final class BasicMultiplyBinaryNumberConverterFunctionTest implements BinaryNumberConverterFunctionTesting2<BasicMultiplyBinaryNumberConverterFunction<FakeConverterContext>, FakeConverterContext>,
    ClassTesting<BasicMultiplyBinaryNumberConverterFunction<FakeConverterContext>>,
    ToStringTesting<BasicMultiplyBinaryNumberConverterFunction<FakeConverterContext>> {

    @Test
    public void testApplyFloatDoubleToInteger() {
        this.applyAndCheck(
            this.createBinaryNumberConverterFunction(),
            2f,
            3.0,
            Integer.class,
            this.createContext(),
            6
        );
    }

    @Test
    public void testApplyIntegerIntegerToInteger() {
        this.applyAndCheck(
            this.createBinaryNumberConverterFunction(),
            2,
            3,
            Integer.class,
            this.createContext(),
            6
        );
    }

    @Test
    public void testApplyIntegerIntegerToLong() {
        this.applyAndCheck(
            this.createBinaryNumberConverterFunction(),
            2,
            3,
            Long.class,
            this.createContext(),
            6L
        );
    }

    @Test
    public void testApplyIntegerIntegerToBigInteger() {
        this.applyAndCheck(
            this.createBinaryNumberConverterFunction(),
            2,
            3,
            BigInteger.class,
            this.createContext(),
            new BigInteger("6")
        );
    }


    @Override
    public BasicMultiplyBinaryNumberConverterFunction<FakeConverterContext> createBinaryNumberConverterFunction() {
        return BasicMultiplyBinaryNumberConverterFunction.instance();
    }

    @Override
    public FakeConverterContext createContext() {
        return new FakeConverterContext() {

            @Override
            public <T> Either<T, String> convert(final Object value,
                                                 final Class<T> target) {
                return this.converter.convert(
                    value,
                    target,
                    this
                );
            }

            private final Converter<FakeConverterContext> converter = Converters.numberToNumber();
        };
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createBinaryNumberConverterFunction(),
            BasicMultiplyBinaryNumberConverterFunction.class.getName()
        );
    }

    // class............................................................................................................

    @Override
    public Class<BasicMultiplyBinaryNumberConverterFunction<FakeConverterContext>> type() {
        return Cast.to(BasicMultiplyBinaryNumberConverterFunction.class);
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}
