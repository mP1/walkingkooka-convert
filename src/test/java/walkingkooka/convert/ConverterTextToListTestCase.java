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

import org.junit.jupiter.api.Test;
import walkingkooka.collect.list.ImmutableList;
import walkingkooka.collect.list.Lists;

import java.util.List;

public abstract class ConverterTextToListTestCase<L extends ImmutableList<E>, E, C extends ConverterTextToList<L, E, ConverterContext>> extends ConverterTestCase2<C> implements ConverterTesting2<C, ConverterContext> {

    ConverterTextToListTestCase() {
        super();
    }

    @Test
    public final void testConvertNullToBooleanListFails() {
        this.convertToListFails(
            null
        );
    }

    @Test
    public final void testConvertEmptyStringToBooleanList() {
        this.convertToListAndCheck(
            ""
        );
    }

    @Test
    public final void testConvertSpacesToBooleanListFails() {
        this.convertToListAndCheck(
            " "
        );
    }

    @Test
    public final void testConvertSpacesToBooleanListFails2() {
        this.convertToListAndCheck(
            "  "
        );
    }

    @Test
    public final void testConvertUnclosedQuotesFails() {
        this.convertToListFails(
            "\""
        );
    }

    @Test
    public final void testConvertUnclosedQuotesFails2() {
        this.convertToListFails(
            "\" "
        );
    }

    final void convertToListAndCheck(final CharSequence text,
                                     final E... elements) {
        this.convertToListAndCheck(
            text,
            this.createContext(),
            elements
        );
    }

    final void convertToListAndCheck(final CharSequence text,
                                     final ConverterContext context,
                                     final E... elements) {
        this.convertToListAndCheck(
            text,
            context,
            Lists.of(elements)
        );
    }

    final void convertToListAndCheck(final CharSequence text,
                                     final ConverterContext context,
                                     final List<E> elements) {
        this.convertAndCheck(
            this.createConverter(),
            text,
            this.listType(),
            context,
            (L) this.emptyList()
                .setElements(elements)
        );
    }

    final void convertToListFails(final CharSequence text) {
        this.convertFails(
            text,
            this.listType()
        );
    }

    abstract Class<L> listType();

    abstract L emptyList();
}
