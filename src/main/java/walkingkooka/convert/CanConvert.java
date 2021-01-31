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
import walkingkooka.text.CharSequences;

/**
 * Interface that includes a method to convert a value to a target type.
 */
public interface CanConvert {

    /**
     * Queries whether this {@link CanConvert} supports converting to the requested {@link Class class}. A returned true
     * does not actually guarantee that the convert method will success, the result should still be tested.
     */
    boolean canConvert(final Object value,
                       final Class<?> type);

    /**
     * Queries whether this {@link CanConvert} supports converting the value to the target type, throwing an exception
     * if it fails, and always returning <code>true</code>
     */
    default boolean canConvertOrFail(final Object value,
                                     final Class<?> target) {
        final boolean can = this.canConvert(value, target);
        if (!can) {
            throw this.convertThrowable("Unable to support convert " + CharSequences.quoteIfChars(value) + " to " + target.getName());
        }

        return can;
    }

    /**
     * Handles converting the given value to the {@link Class target type}.
     */
    <T> Either<T, String> convert(final Object value,
                                  final Class<T> target);

    /**
     * Converts the given value to the {@link Class target type} or throws a {@link ConversionException}
     */
    default <T> T convertOrFail(final Object value,
                                final Class<T> target) {
        final Either<T, String> converted = this.convert(value, target);
        if (converted.isRight()) {
            throw this.convertThrowable(converted.rightValue());
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
     * Creates a {@link Throwable} which may then be thrown to report a convert failure.
     */
    default RuntimeException convertThrowable(final String message) {
        return new ConversionException(message);
    }
}
