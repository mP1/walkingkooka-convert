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
import walkingkooka.math.DecimalNumberSymbols;
import walkingkooka.props.Properties;

/**
 * Converts a {@link Properties} into a {@link DecimalNumberSymbols}.
 */
final class ConverterPropertiesToDecimalNumberSymbols<C extends ConverterContext> extends ConverterPropertiesTo<DecimalNumberSymbols, C> {

    /**
     * Type safe singleton getter
     */
    static <C extends ConverterContext> ConverterPropertiesToDecimalNumberSymbols<C> instance() {
        return Cast.to(INSTANCE);
    }

    private final static ConverterPropertiesToDecimalNumberSymbols<?> INSTANCE = new ConverterPropertiesToDecimalNumberSymbols<>();

    private ConverterPropertiesToDecimalNumberSymbols() {
        super();
    }

    @Override
    DecimalNumberSymbols fromProperties(final Properties properties) {
        return DecimalNumberSymbols.fromProperties(properties);
    }

    @Override
    Class<DecimalNumberSymbols> type() {
        return DecimalNumberSymbols.class;
    }
}
