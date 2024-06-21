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

final class ConverterNumberNumberNumberTypeVisitorShort extends ConverterNumberNumberNumberTypeVisitorNumber<Short> {

    static ConverterNumberNumberNumberTypeVisitorShort with() {
        return new ConverterNumberNumberNumberTypeVisitorShort();
    }

    ConverterNumberNumberNumberTypeVisitorShort() {
        super();
    }

    @Override
    protected void visit(final BigDecimal number) {
        this.save(number.shortValueExact());
    }

    @Override
    protected void visit(final BigInteger number) {
        this.save(number.shortValueExact());
    }

    @Override
    protected void visit(final Byte number) {
        this.saveShort(number);
    }

    @Override
    protected void visit(final Double number) {
        if (number >= Short.MIN_VALUE && number <= Short.MAX_VALUE) {
            this.save(
                    number.shortValue()
            );
        }
    }

    @Override
    protected void visit(final Float number) {
        if (number >= Short.MIN_VALUE && number <= Short.MAX_VALUE) {
            this.save(
                    number.shortValue()
            );
        }
    }

    @Override
    protected void visit(final Integer number) {
        if (number >= Short.MIN_VALUE && number <= Short.MAX_VALUE) {
            this.save(
                    number.shortValue()
            );
        }
    }

    @Override
    protected void visit(final Long number) {
        if (number >= Short.MIN_VALUE && number <= Short.MAX_VALUE) {
            this.save(
                    number.shortValue()
            );
        }
    }

    @Override
    protected void visit(final Short number) {
        this.save(number);// dead code because Short to Short is short circuited earlier by ConverterNumberNumber.
    }

    private void saveShort(final Number number) {
        this.save(number.shortValue());
    }

    @Override
    Class<Short> targetType() {
        return Short.class;
    }
}
