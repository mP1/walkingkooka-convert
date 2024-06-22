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

import walkingkooka.ToStringBuilder;
import walkingkooka.math.NumberTypeVisitor;

final class ConverterNumberToNumberNumberTypeVisitor extends NumberTypeVisitor {

    static ConverterNumberToNumberNumberTypeVisitorNumber<?> visitor(final Class<?> type) {
        final ConverterNumberToNumberNumberTypeVisitor visitor = new ConverterNumberToNumberNumberTypeVisitor();
        visitor.accept(type);
        return visitor.visitor;
    }

    ConverterNumberToNumberNumberTypeVisitor() {
        super();
    }

    @Override
    protected void visitBigDecimal() {
        this.visitor = ConverterNumberToNumberNumberTypeVisitorBigDecimal.with();
    }

    @Override
    protected void visitBigInteger() {
        this.visitor = ConverterNumberToNumberNumberTypeVisitorBigInteger.with();
    }

    @Override
    protected void visitByte() {
        this.visitor = ConverterNumberToNumberNumberTypeVisitorByte.with();
    }

    @Override
    protected void visitDouble() {
        this.visitor = ConverterNumberToNumberNumberTypeVisitorDouble.with();
    }

    @Override
    protected void visitFloat() {
        this.visitor = ConverterNumberToNumberNumberTypeVisitorFloat.with();
    }

    @Override
    protected void visitInteger() {
        this.visitor = ConverterNumberToNumberNumberTypeVisitorInteger.with();
    }

    @Override
    protected void visitLong() {
        this.visitor = ConverterNumberToNumberNumberTypeVisitorLong.with();
    }

    @Override
    protected void visitShort() {
        this.visitor = ConverterNumberToNumberNumberTypeVisitorShort.with();
    }

    private ConverterNumberToNumberNumberTypeVisitorNumber<?> visitor;

    @Override
    public String toString() {
        return ToStringBuilder.empty().value(this.visitor).build();
    }
}
