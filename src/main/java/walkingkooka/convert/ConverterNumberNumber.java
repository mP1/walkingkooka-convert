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

import walkingkooka.Cast;
import walkingkooka.Either;
import walkingkooka.math.Maths;

import java.util.Objects;

/**
 * A {@link Converter} which handles converting {@link Number} to other number types or nothing at all if the target is number.
 */
final class ConverterNumberNumber<C extends ConverterContext> extends Converter2<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterNumberNumber<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterNumberNumber INSTANCE = new ConverterNumberNumber();

    private ConverterNumberNumber() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        Objects.requireNonNull(value, "value");
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(context, "context");

        return value instanceof Number && (Number.class == type || Maths.isNumberClass(type));
    }

    @Override
    <T> Either<T, String> convert0(final Object value,
                                   final Class<T> type,
                                   final ConverterContext context) {
        Either<T, String> result;
        try {
            result = type == Number.class ?
                    Either.left(Cast.to(value)) :
                    this.convertNonNumber(value, type);
        } catch (final RuntimeException cause) {
            result = this.failConversion(value, type, cause);
        }
        return result;
    }

    private <T> Either<T, String> convertNonNumber(final Object value,
                                                   final Class<T> type) {
        final ConverterNumberNumberNumberTypeVisitorNumber<?> visitor = ConverterNumberNumberNumberTypeVisitor.visitor(type);
        return null == visitor ?
                this.failConversion(value, type) :
                Either.left(Cast.to(visitor.convert(Cast.to(value))));
    }

    @Override
    public String toString() {
        return "Number->Number";
    }
}
