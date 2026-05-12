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

/**
 * A {@link java.util.function.Function} that accepts two {@link Number} producing a single {@link Number} of the
 * requested type.
 * <br>
 * The initial use case is converting a {@link walkingkooka.currency.CurrencyValue} into a {@link Number}
 * using {@link walkingkooka.currency.CurrencyExchangeRater} using as the target {@link walkingkooka.currency.HasCurrencyCode}.
 */
public interface BinaryNumberConverterFunction<C extends ConverterContext> {

    <N extends Number> N apply(final Number left,
                               final Number right,
                               final Class<N> targetType,
                               final C context);
}
