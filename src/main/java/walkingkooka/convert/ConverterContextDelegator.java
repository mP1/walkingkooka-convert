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
import walkingkooka.datetime.DateTimeContext;
import walkingkooka.datetime.DateTimeContextDelegator;
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.math.DecimalNumberContextDelegator;

import java.util.Locale;

public interface ConverterContextDelegator extends ConverterContext,
        DateTimeContextDelegator,
        DecimalNumberContextDelegator {

    @Override
    default DateTimeContext dateTimeContext() {
        return this.converterContext();
    }

    @Override
    default DecimalNumberContext decimalNumberContext() {
        return this.converterContext();
    }

    @Override
    default Locale locale() {
        return this.converterContext().locale();
    }

    @Override
    default long dateOffset() {
        return this.converterContext()
                .dateOffset();
    }

    @Override
    default boolean canConvert(final Object value,
                               final Class<?> type) {
        return this.converterContext()
                .canConvert(
                        value,
                        type
                );
    }

    @Override
    default <T> Either<T, String> convert(final Object value,
                                          final Class<T> target) {
        return this.converterContext()
                .convert(
                        value,
                        target
                );
    }

    ConverterContext converterContext();
}
