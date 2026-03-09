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
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.props.Properties;

/**
 * Converts a {@link Properties} into a {@link walkingkooka.datetime.DateTimeSymbols}.
 */
final class ConverterPropertiesToDateTimeSymbols<C extends ConverterContext> extends ConverterPropertiesTo<DateTimeSymbols, C> {

    /**
     * Type safe singleton getter
     */
    static <C extends ConverterContext> ConverterPropertiesToDateTimeSymbols<C> instance() {
        return Cast.to(INSTANCE);
    }

    private final static ConverterPropertiesToDateTimeSymbols<?> INSTANCE = new ConverterPropertiesToDateTimeSymbols<>();

    private ConverterPropertiesToDateTimeSymbols() {
        super();
    }

    @Override
    DateTimeSymbols fromProperties(final Properties properties) {
        return DateTimeSymbols.fromProperties(properties);
    }

    @Override
    Class<DateTimeSymbols> type() {
        return DateTimeSymbols.class;
    }
}
