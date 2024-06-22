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

import java.math.BigDecimal;
import java.math.BigInteger;

final class ConverterNumberToNumberNumberTypeVisitorDouble extends ConverterNumberToNumberNumberTypeVisitorNumber<Double> {

    static ConverterNumberToNumberNumberTypeVisitorDouble with() {
        return new ConverterNumberToNumberNumberTypeVisitorDouble();
    }

    ConverterNumberToNumberNumberTypeVisitorDouble() {
        super();
    }

    @Override
    protected void visit(final BigDecimal number) {
        if (number.compareTo(BIG_DECIMAL_DOUBLE_MIN) >= 0 && number.compareTo(BIG_DECIMAL_DOUBLE_MAX) <= 0) {
            this.save(
                    number.doubleValue()
            );
        }
    }

    private final static BigDecimal BIG_DECIMAL_DOUBLE_MIN = BigDecimal.valueOf(-Double.MAX_VALUE);

    private final static BigDecimal BIG_DECIMAL_DOUBLE_MAX = BigDecimal.valueOf(Double.MAX_VALUE);

    @Override
    protected void visit(final BigInteger number) {
        if (number.compareTo(BIG_INTEGER_DOUBLE_MIN) >= 0 && number.compareTo(BIG_INTEGER_DOUBLE_MAX) <= 0) {
            this.save(
                    number.doubleValue()
            );
        }
    }

    private final static BigInteger BIG_INTEGER_DOUBLE_MIN = BIG_DECIMAL_DOUBLE_MIN.toBigInteger();

    private final static BigInteger BIG_INTEGER_DOUBLE_MAX = BIG_DECIMAL_DOUBLE_MAX.toBigInteger();

    @Override
    protected void visit(final Byte number) {
        this.saveDouble(number);
    }

    @Override
    protected void visit(final Double number) {
        this.save(number);
    }

    @Override
    protected void visit(final Float number) {
        this.saveDouble(number);
    }

    @Override
    protected void visit(final Integer number) {
        this.saveDouble(number);
    }

    @Override
    protected void visit(final Long number) {
        final double converted = number.doubleValue();
        if ((long) converted == number) {
            this.save(converted);
        }
    }

    @Override
    protected void visit(final Short number) {
        this.saveDouble(number);
    }

    private void saveDouble(final Number number) {
        this.save(number.doubleValue());
    }

    @Override
    Class<Double> targetType() {
        return Double.class;
    }
}
