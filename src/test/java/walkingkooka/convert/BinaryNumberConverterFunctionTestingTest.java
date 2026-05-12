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
import walkingkooka.convert.BinaryNumberConverterFunctionTestingTest.TestBinaryNumberConverterFunction;

import java.math.BigDecimal;

public final class BinaryNumberConverterFunctionTestingTest implements BinaryNumberConverterFunctionTesting<TestBinaryNumberConverterFunction, FakeConverterContext> {

    @Test
    public void testApply() {
        this.applyAndCheck(
            new TestBinaryNumberConverterFunction(),
            2L,
            3L,
            BigDecimal.class,
            new FakeConverterContext(),
            new BigDecimal(2 * 3)
        );
    }

    final static class TestBinaryNumberConverterFunction implements BinaryNumberConverterFunction<FakeConverterContext> {

        @Override
        public <N extends Number> N apply(final Number left,
                                          final Number right,
                                          final Class<N> targetType,
                                          final FakeConverterContext context) {
            return targetType.cast(
                new BigDecimal(
                    left.longValue() * right.longValue()
                )
            );
        }
    }
}
