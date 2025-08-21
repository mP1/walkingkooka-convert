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

/**
 * A templated converter that adds guards to the convert method leaving a single method that must succeed when invoked.
 */
public interface TryingShortCircuitingConverter<C extends ConverterContext> extends ShortCircuitingConverter<C> {

    @Override
    default <T> Either<T, String> doConvert(final Object value,
                                            final Class<T> type,
                                            final C context) {
        Either<T, String> result;

        try {
            result = this.successfulConversion(
                this.tryConvertOrFail(
                    value,
                    type,
                    context
                ),
                type
            );
        } catch (final Error cause) {
            throw cause;
        } catch (final UnsupportedOperationException cause) {
            throw cause;
        } catch (final RuntimeException cause) {
            result = Either.right(cause.getMessage());
        }

        return result;
    }

    /**
     * This method must accept the value and return a value, throwing an exception if the attempt fails.
     */
    Object tryConvertOrFail(final Object value,
                            final Class<?> type,
                            final C context);
}
