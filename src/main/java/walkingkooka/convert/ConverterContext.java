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

import walkingkooka.Context;
import walkingkooka.Either;
import walkingkooka.datetime.DateTimeContext;
import walkingkooka.math.DecimalNumberContext;

/**
 * {@link Context} that accompanies a {@link Converter} and is intended to carry values that may be locale or user aware.
 */
public interface ConverterContext extends CanConvert,
    DateTimeContext,
    DecimalNumberContext {

    /**
     * The offset (relative to 1/1/1970) added when converting a number to a {@link java.time.LocalDate}.
     */
    long dateOffset();

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
}
