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

import walkingkooka.math.HasMathContext;
import walkingkooka.math.NumberVisitor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Handles performing the multiplication of two numbers assuming they have both been converted to the same type.
 */
final class BasicMultiplyBinaryNumberConverterFunctionNumberVisitor extends NumberVisitor {

    static Number multiply(final Number left,
                           final Number right,
                           final HasMathContext hasMathContext) {
        final BasicMultiplyBinaryNumberConverterFunctionNumberVisitor visitor = new BasicMultiplyBinaryNumberConverterFunctionNumberVisitor(
            left,
            hasMathContext
        );
        visitor.accept(right);
        return visitor.result;
    }

    // @VisibleForTesting
    BasicMultiplyBinaryNumberConverterFunctionNumberVisitor(final Number left,
                                                            final HasMathContext hasMathContext) {
        super();
        this.left = left;
        this.hasMathContext = hasMathContext;
    }

    @Override
    protected void visit(final BigDecimal number) {
        this.result = ((BigDecimal) this.left).multiply(
            number,
            this.hasMathContext.mathContext()
        );
    }

    @Override
    protected void visit(final BigInteger number) {
        this.result = ((BigInteger) this.left).multiply(number);
    }

    @Override
    protected void visit(final Byte number) {
        this.result = this.left.byteValue() * number;
    }

    @Override
    protected void visit(final Double number) {
        this.result = this.left.doubleValue() * number;
    }

    @Override
    protected void visit(final Float number) {
        this.result = this.left.floatValue() * number;
    }

    @Override
    protected void visit(final Integer number) {
        this.result = this.left.intValue() * number;
    }

    @Override
    protected void visit(final Long number) {
        this.result = this.left.longValue() * number;
    }

    @Override
    protected void visit(final Short number) {
        this.result = this.left.shortValue() * number;
    }

    @Override
    protected void visitUnknown(final Number number) {
        throw new UnsupportedOperationException();
    }

    private final Number left;

    private final HasMathContext hasMathContext;

    private Number result;

    // Object...........................................................................................................

    @Override
    public String toString() {
        return this.left + " * ? = " + this.result;
    }
}
