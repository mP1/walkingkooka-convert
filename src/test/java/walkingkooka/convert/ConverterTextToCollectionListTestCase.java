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

import walkingkooka.collect.list.ImmutableList;
import walkingkooka.collect.list.Lists;

import java.util.List;

public abstract class ConverterTextToCollectionListTestCase<L extends ImmutableList<E>,
    E,
    C extends ConverterTextToCollectionList<L, E, ConverterContext>> extends ConverterTextToCollectionTestCase<L, E, List<E>, C> {

    ConverterTextToCollectionListTestCase() {
        super();
    }

    @Override //
    final void convertToCollectionAndCheck(final CharSequence text,
                                           final ConverterContext context,
                                           final E... elements) {
        this.convertToCollectionAndCheck(
            text,
            context,
            Lists.of(elements)
        );
    }

    final void convertToCollectionAndCheck(final CharSequence text,
                                           final ConverterContext context,
                                           final List<E> elements) {
        this.convertAndCheck(
            this.createConverter(),
            text,
            this.collectionType(),
            context,
            (L) this.emptyList()
                .setElements(elements)
        );
    }

    abstract L emptyList();
}
