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
import walkingkooka.collect.set.CsvStringSet;

import java.util.Set;

/**
 * A Converter that handles converting a CSV {@link String} to a {@link CsvStringSet}.
 */
final class ConverterTextToCollectionSetCsvStringSet<C extends ConverterContext> extends ConverterTextToCollectionSet<CsvStringSet, String, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTextToCollectionSetCsvStringSet<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterTextToCollectionSetCsvStringSet<?> INSTANCE = new ConverterTextToCollectionSetCsvStringSet<>();

    private ConverterTextToCollectionSetCsvStringSet() {
        super();
    }

    @Override
    Class<CsvStringSet> targetType() {
        return CsvStringSet.class;
    }

    @Override
    Class<String> elementType() {
        return String.class;
    }

    @Override
    CsvStringSet toImmutableCollection(final Set<String> mutableCollection) {
        return CsvStringSet.EMPTY.setElements(mutableCollection);
    }
}
