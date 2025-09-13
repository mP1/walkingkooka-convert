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
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.math.Maths;

import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.util.function.Function;

/**
 * A {@link Converter} that formats {@link Number numbers}.
 */
final class ConverterDecimalFormatStringToNumber<C extends ConverterContext> extends ConverterDecimalFormat<C> {

    static <C extends ConverterContext> ConverterDecimalFormatStringToNumber<C> with(final Function<DecimalNumberContext, DecimalFormat> decimalFormat) {
        return new ConverterDecimalFormatStringToNumber<>(decimalFormat);
    }

    private ConverterDecimalFormatStringToNumber(final Function<DecimalNumberContext, DecimalFormat> decimalFormat) {
        super(decimalFormat);
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return (null == value || value instanceof String) &&
            (Maths.isNumberClass(type) || type == Number.class);
    }

    @Override //
    <T> Either<T, String> convertWithDecimalFormat(final DecimalFormat decimalFormat,
                                                   final Object value,
                                                   final Class<T> type,
                                                   final ConverterContext context) {
        final Number parsed = decimalFormat.parse(value.toString(), new ParsePosition(0));
        return null == parsed ?
            this.failConversion(value, type) :
            this.convertToNumber(parsed,
                type,
                context);
    }

    /**
     * Helper that performs the last step by converting a {@link Number} to another {@link Number sub class}.
     */
    private <N> Either<N, String> convertToNumber(final Number number,
                                                  final Class<N> type,
                                                  final ConverterContext context) {
        return ConverterNumberToNumber.instance()
            .convert(
                number,
                type,
                context
            );
    }

    @Override
    public String toString() {
        return "DecimalFormat String to Number";
    }
}
