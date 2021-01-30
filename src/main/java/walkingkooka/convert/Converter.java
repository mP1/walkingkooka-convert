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
import walkingkooka.text.CharSequences;

/**
 * Converts an object instance to a requested target {@link Class class}.
 */
public interface Converter<C extends ConverterContext> {

    /**
     * Queries whether this {@link Converter} supports converting to the requested {@link Class class}. A returned true
     * does not actually guarantee that the convert method will success, the result should still be tested.
     */
    boolean canConvert(final Object value,
                       final Class<?> type,
                       final C context);

    /**
     * Queries whether this {@link Converter} supports converting the value to the target type, throwing an exception
     * if it fails, and always returning <code>true</code>
     */
    default boolean canConvertOrFail(final Object value,
                                     final Class<?> target,
                                     final C context) {
        final boolean can = this.canConvert(value, target, context);
        if (!can) {
            throw new ConversionException("Unable to support convert " + CharSequences.quoteIfChars(value) + " to " + target.getName());
        }

        return can;
    }

    /**
     * Converts the given value to the requested type returning an {@link Either} with {@link Either#leftValue()} holding
     * the result or {@link Either#rightValue()} holding an failure message.
     */
    <T> Either<T, String> convert(final Object value,
                                  final Class<T> type,
                                  final C context);

    /**
     * Converts the given value to the {@link Class target type} or throws a {@link ConversionException}
     */
    default <T> T convertOrFail(final Object value,
                                final Class<T> target,
                                final C context) {
        final Either<T, String> converted = this.convert(value, target, context);
        if (converted.isRight()) {
            throw new ConversionException(converted.rightValue());
        }

        return converted.leftValue();
    }

    /**
     * Useful to report a failed conversion with a standard error message.
     */
    default <T> Either<T, String> failConversion(final Object value,
                                                 final Class<T> target) {
        return FailConversion.handle(value, target);
    }

    /**
     * Useful to report a failed conversion with a standard error message, which includes a {@link Throwable#getMessage()}.
     */
    default <T> Either<T, String> failConversion(final Object value,
                                                 final Class<T> target,
                                                 final Throwable cause) {
        return FailConversion.handle(value, target, cause);
    }

    /**
     * Replaces the current {@link Object#toString()} with a new one.
     */
    default Converter<C> setToString(final String toString) {
        return Converters.customToString(this, toString);
    }

    /**
     * Helper that makes casting and working around generics a little less noisy.
     */
    default <C extends ConverterContext> Converter<C> cast(final Class<C> type) {
        return Cast.to(this);
    }
}
