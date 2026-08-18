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

import walkingkooka.HasCharsetTesting;
import walkingkooka.currency.CanCurrencyExchangeRateTesting;
import walkingkooka.currency.CanCurrencyExchangesTesting;
import walkingkooka.currency.CanCurrencyForLocaleTesting;
import walkingkooka.currency.CurrencyCodeLanguageTagContextTesting;
import walkingkooka.currency.HasCurrencyCodeTesting;
import walkingkooka.datetime.DateTimeContextTesting;
import walkingkooka.locale.CanDateTimeSymbolsForLocaleTesting;
import walkingkooka.locale.CanDecimalNumberSymbolsForLocaleTesting;
import walkingkooka.math.DecimalNumberContextTesting;
import walkingkooka.text.BinaryTextContextTesting;

/**
 * Mixing testing interface for {@link ConverterContext}
 */
public interface ConverterContextTesting extends ConverterLikeTesting,
    BinaryTextContextTesting,
    CanCurrencyExchangesTesting,
    CanCurrencyExchangeRateTesting,
    CanCurrencyForLocaleTesting,
    CanDateTimeSymbolsForLocaleTesting,
    CanDecimalNumberSymbolsForLocaleTesting,
    CurrencyCodeLanguageTagContextTesting,
    DateTimeContextTesting,
    DecimalNumberContextTesting,
    HasCharsetTesting,
    HasCurrencyCodeTesting {

    // multiply.........................................................................................................

    default <N extends Number> void multiplyAndCheck(final ConverterContext context,
                                                     final Number left,
                                                     final Number right,
                                                     final Class<N> type,
                                                     final N expected) {
        this.checkEquals(
            expected,
            context.multiply(
                left,
                right,
                type
            )
        );
    }
}
