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

import java.util.Objects;

/**
 * A converter that guards calls to a convert method, only if the canConvert passes.
 */
public interface ShortCircuitingConverter<C extends ConverterContext> extends Converter<C> {

    @Override
    default <T> Either<T, String> convert(final Object value,
                                          final Class<T> type,
                                          final C context) {
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(context, "context");

        return this.canConvert(
            value,
            type,
            context
        ) ?
            this.doConvert(
                value,
                type,
                context
            ) :
            this.failConversion(
                value,
                type
            );
    }

    <T> Either<T, String> doConvert(final Object value,
                                    final Class<T> type,
                                    final C context);
}
