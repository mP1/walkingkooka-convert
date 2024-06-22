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

final class ConverterNumberToNumberNumberTypeVisitorInteger extends ConverterNumberToNumberNumberTypeVisitorNumber<Integer> {

    static ConverterNumberToNumberNumberTypeVisitorInteger with() {
        return new ConverterNumberToNumberNumberTypeVisitorInteger();
    }

    ConverterNumberToNumberNumberTypeVisitorInteger() {
        super();
    }

    @Override
    protected void visit(final BigDecimal number) {
        this.save(number.intValueExact());
    }

    @Override
    protected void visit(final BigInteger number) {
        this.save(number.intValueExact());
    }

    @Override
    protected void visit(final Byte number) {
        this.save(number.intValue());
    }

    @Override
    protected void visit(final Double number) {
        if (number >= Integer.MIN_VALUE && number <= Integer.MAX_VALUE) {
            this.save(
                    number.intValue()
            );
        }
    }

    @Override
    protected void visit(final Float number) {
        if (number >= Integer.MIN_VALUE && number <= Integer.MAX_VALUE) {
            this.save(
                    number.intValue()
            );
        }
    }

    @Override
    protected void visit(final Integer number) {
        this.save(number);
    }

    @Override
    protected void visit(final Long number) {
        if (number >= Integer.MIN_VALUE && number <= Integer.MAX_VALUE) {
            this.save(
                    number.intValue()
            );
        }
    }

    @Override
    protected void visit(final Short number) {
        if (number >= Integer.MIN_VALUE && number <= Integer.MAX_VALUE) {
            this.save(
                    number.intValue()
            );
        }
    }

    @Override
    Class<Integer> targetType() {
        return Integer.class;
    }
}
