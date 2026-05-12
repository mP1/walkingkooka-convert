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

package walkingkooka.convert.math;

import walkingkooka.convert.ConverterContext;
import walkingkooka.currency.CurrencyValue;

/**
 * A function that accepts two numbers, performs some operation such as multiplication and gives the answer
 * in the requested targeted type.
 * <br>
 * This is intended to support converting a {@link CurrencyValue#value()} into the current {@link ConverterContext#currencyCode()},
 * using the exchange rate returned by {@link ConverterContext#exchangeRate}
 */
public interface BinaryExpressionConverterFunction<C extends ConverterContext> {

    <T extends Number> T apply(final Number left,
                               final Number right,
                               final Class<T> targetType,
                               final C context);
}
