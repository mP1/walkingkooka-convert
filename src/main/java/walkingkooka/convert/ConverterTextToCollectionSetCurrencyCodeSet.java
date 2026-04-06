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
import walkingkooka.currency.CurrencyCode;
import walkingkooka.currency.CurrencyCodeSet;

import java.util.Set;

/**
 * A Converter that handles converting a CSV {@link String} to a {@link CurrencyCodeSet}.
 */
final class ConverterTextToCollectionSetCurrencyCodeSet<C extends ConverterContext> extends ConverterTextToCollectionSet<CurrencyCodeSet, CurrencyCode, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTextToCollectionSetCurrencyCodeSet<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterTextToCollectionSetCurrencyCodeSet<?> INSTANCE = new ConverterTextToCollectionSetCurrencyCodeSet<>();

    private ConverterTextToCollectionSetCurrencyCodeSet() {
        super();
    }

    @Override
    Class<CurrencyCodeSet> targetType() {
        return CurrencyCodeSet.class;
    }

    @Override
    Class<CurrencyCode> elementType() {
        return CurrencyCode.class;
    }

    @Override
    CurrencyCodeSet toImmutableCollection(final Set<CurrencyCode> mutableCollection) {
        return CurrencyCodeSet.EMPTY.setElements(mutableCollection);
    }
}
