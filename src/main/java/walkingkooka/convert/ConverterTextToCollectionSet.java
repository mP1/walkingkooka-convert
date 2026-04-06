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

import walkingkooka.collect.set.ImmutableSet;
import walkingkooka.collect.set.SortedSets;

import java.util.Set;

/**
 * Base class that includes the logic for parsing a string with a separator and converting each element to the element type.
 */
abstract class ConverterTextToCollectionSet<L extends ImmutableSet<E>, E, C extends ConverterContext> extends ConverterTextToCollection<L, Set<E>, E, C> {

    ConverterTextToCollectionSet() {
        super();
    }

    @Override
    final Set<E> mutableCollection() {
        return SortedSets.tree();
    }
}
