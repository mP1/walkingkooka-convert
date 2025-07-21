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

import walkingkooka.Either;
import walkingkooka.ToStringBuilder;
import walkingkooka.math.NumberVisitor;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * {@link NumberVisitor} that dispatches based on the {@link Number} type back to the {@link ConverterNumber} to convert.
 */
final class ConverterNumberToNumberVisitor<T> extends NumberVisitor {

    static <T, C extends ConverterContext> Either<T, String> convert(final ConverterNumber<T, ?> converter,
                                                                     final Number number,
                                                                     final Class<T> type,
                                                                     final ConverterContext context) {
        final ConverterNumberToNumberVisitor<T> visitor = new ConverterNumberToNumberVisitor<>(
            converter,
            type,
            context
        );
        visitor.accept(number);
        return visitor.value;
    }

    ConverterNumberToNumberVisitor(final ConverterNumber<T, ?> converter,
                                   final Class<T> type,
                                   final ConverterContext context) {
        super();
        this.converter = converter;
        this.type = type;
        this.context = context;
    }

    @Override
    protected void visit(final BigDecimal number) {
        this.value = this.converter.bigDecimal(
            number,
            this.context
        );
    }

    @Override
    protected void visit(final BigInteger number) {
        this.value = this.converter.bigInteger(
            number,
            this.context
        );
    }

    @Override
    protected void visit(final Byte number) {
        this.value = this.converter.number(
            number,
            this.context
        );
    }

    @Override
    protected void visit(final Double number) {
        this.value = this.converter.doubleValue(
            number,
            this.context
        );
    }

    @Override
    protected void visit(final Float number) {
        this.value = this.converter.floatValue(
            number,
            this.context
        );
    }

    @Override
    protected void visit(final Integer number) {
        this.value = this.converter.number(
            number,
            this.context
        );
    }

    @Override
    protected void visit(final Long number) {
        this.value = this.converter.longValue(
            number,
            this.context
        );
    }

    @Override
    protected void visit(final Short number) {
        this.value = this.converter.number(
            number,
            this.context
        );
    }

    @Override
    protected void visitUnknown(final Number number) {
        this.value = this.converter.failConversion(number, this.type);
    }

    private final ConverterNumber<T, ?> converter;

    private Either<T, String> value;
    private final Class<T> type;

    private final ConverterContext context;

    @Override
    public String toString() {
        return ToStringBuilder.empty()
            .value(this.converter)
            .value(this.value)
            .value(this.type.getName())
            .value(this.context)
            .build();
    }
}
