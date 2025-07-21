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

import walkingkooka.math.NumberVisitor;

abstract class ConverterNumberToNumberNumberTypeVisitorNumber<N extends Number> extends NumberVisitor {

    /**
     * Necessary to support unsigned byte values, when converting a byte to another number type.
     */
    static int toUnsignedInt(final byte value) {
        return 0xff & value;
    }

    ConverterNumberToNumberNumberTypeVisitorNumber() {
        super();
    }

    final N convert(final Number number) {
        this.accept(number);
        return converted;
    }

    final void save(final N number) {
        this.converted = number;
    }

    private N converted;

    abstract Class<N> targetType();

    @Override
    public final String toString() {
        return Number.class.getSimpleName() + "->" + this.targetType().getSimpleName();
    }
}
