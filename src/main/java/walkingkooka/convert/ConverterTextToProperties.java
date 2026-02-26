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
import walkingkooka.props.Properties;

/**
 * A {@link Converter} that reads {@link Properties} from a {@link String}.
 */
final class ConverterTextToProperties<C extends ConverterContext> extends ConverterTextTo<Properties, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTextToProperties<C> instance() {
        return Cast.to(INSTANCE);
    }

    private final static ConverterTextToProperties<?> INSTANCE = new ConverterTextToProperties<>();

    private ConverterTextToProperties() {
        super();
    }

    @Override
    Class<Properties> targetType() {
        return Properties.class;
    }

    @Override
    public Object parseText(final String value,
                            final Class<?> type,
                            final C context) {
        return Properties.parse(value);
    }
}
