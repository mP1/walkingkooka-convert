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
import walkingkooka.ToStringTesting;
import walkingkooka.math.NumberTypeVisitorTesting;
import walkingkooka.reflect.JavaVisibility;

import java.math.BigInteger;

public class ConverterNumberToNumberNumberTypeVisitorTest implements NumberTypeVisitorTesting<ConverterNumberToNumberNumberTypeVisitor>,
    ToStringTesting<ConverterNumberToNumberNumberTypeVisitor> {

    @Test
    public void testToString() {
        final ConverterNumberToNumberNumberTypeVisitor visitor = new ConverterNumberToNumberNumberTypeVisitor();
        visitor.accept(BigInteger.class);
        this.toStringAndCheck(visitor, "Number->BigInteger");
    }

    @Override
    public ConverterNumberToNumberNumberTypeVisitor createVisitor() {
        return new ConverterNumberToNumberNumberTypeVisitor();
    }

    @Override
    public String typeNamePrefix() {
        return ConverterNumberToNumber.class.getSimpleName();
    }

    @Override
    public Class<ConverterNumberToNumberNumberTypeVisitor> type() {
        return ConverterNumberToNumberNumberTypeVisitor.class;
    }

    @Override
    public JavaVisibility typeVisibility() {
        return JavaVisibility.PACKAGE_PRIVATE;
    }
}
