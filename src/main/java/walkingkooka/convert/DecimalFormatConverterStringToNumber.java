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
final class DecimalFormatConverterStringToNumber<C extends ConverterContext> extends DecimalFormatConverter<C> {

    static <C extends ConverterContext> DecimalFormatConverterStringToNumber<C> with(final Function<DecimalNumberContext, DecimalFormat> decimalFormat) {
        return new DecimalFormatConverterStringToNumber<>(decimalFormat);
    }

    private DecimalFormatConverterStringToNumber(final Function<DecimalNumberContext, DecimalFormat> decimalFormat) {
        super(decimalFormat);
    }

    @Override
    boolean canConvertNonNull(final Object value,
                              final Class<?> type,
                              final C context) {
        return value instanceof String;
    }

    @Override
    boolean canConvertType(final Class<?> type) {
        return Maths.isNumberClass(type) || type == Number.class;
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

    @Override
    public String toString() {
        return "DecimalFormat String to Number";
    }
}
