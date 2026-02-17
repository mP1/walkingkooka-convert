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

import walkingkooka.currency.CanCurrencyForLocaleTesting2;
import walkingkooka.datetime.DateTimeContextTesting2;
import walkingkooka.locale.CanDateTimeSymbolsForLocaleTesting2;
import walkingkooka.locale.CanDecimalNumberSymbolsForLocaleTesting2;
import walkingkooka.math.DecimalNumberContextTesting2;
import walkingkooka.text.HasIndentationTesting;
import walkingkooka.text.HasLineEndingTesting;

/**
 * Mixing testing interface for {@link ConverterContext}
 */
public interface ConverterContextTesting<C extends ConverterContext> extends ConverterLikeTesting<C>,
    CanCurrencyForLocaleTesting2<C>,
    CanDateTimeSymbolsForLocaleTesting2<C>,
    CanDecimalNumberSymbolsForLocaleTesting2<C>,
    DateTimeContextTesting2<C>,
    DecimalNumberContextTesting2<C>,
    HasIndentationTesting,
    HasLineEndingTesting {

    @Override
    default C createCanCurrencyForLocale() {
        return this.createContext();
    }

    @Override
    default C createCanDateTimeSymbolsForLocale() {
        return this.createContext();
    }

    @Override
    default C createCanDecimalNumberSymbolsForLocale() {
        return this.createContext();
    }

    /**
     * Delegates to {@link #createContext()}.
     */
    @Override
    default C createConverterLike() {
        return this.createContext();
    }

    @Override
    default String typeNameSuffix() {
        return ConverterContext.class.getSimpleName();
    }
}
