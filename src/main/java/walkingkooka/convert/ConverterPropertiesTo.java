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

import walkingkooka.props.HasProperties;
import walkingkooka.props.Properties;

/**
 * Converts a {@link Properties} into a {@link walkingkooka.props.HasProperties}. Note the source value will be converted to a {@link Properties},
 * and then static fromProperties factory method called.
 */
abstract class ConverterPropertiesTo<T extends HasProperties, C extends ConverterContext> implements TryingShortCircuitingConverter<C> {

    ConverterPropertiesTo() {
        super();
    }


    @Override
    public final boolean canConvert(final Object value,
                                    final Class<?> type,
                                    final C context) {
        return this.type() == type &&
            context.canConvert(
                value,
                Properties.class
            );
    }

    @Override
    public final Object tryConvertOrFail(final Object value,
                                         final Class<?> type,
                                         final C context) {
        return this.fromProperties(
            context.convertOrFail(
                value,
                Properties.class
            )
        );
    }

    abstract T fromProperties(final Properties properties);

    abstract Class<T> type();

    @Override
    public String toString() {
        return Properties.class.getSimpleName() + " to " + this.type().getSimpleName();
    }
}
