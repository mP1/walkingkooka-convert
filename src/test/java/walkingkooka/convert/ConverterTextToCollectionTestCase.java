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

import java.util.Collection;

public abstract class ConverterTextToCollectionTestCase<IMMUTABLE_COLLECTION extends Collection<ELEMENT>,
    ELEMENT,
    MUTABLE_COLLECTION extends Collection<ELEMENT>,
    CONVERTER extends ConverterTextToCollection<IMMUTABLE_COLLECTION, MUTABLE_COLLECTION, ELEMENT, ConverterContext>> extends ConverterTextToTestCase<CONVERTER, IMMUTABLE_COLLECTION> {

    ConverterTextToCollectionTestCase() {
        super();
    }

    @Test
    public final void testConvertNullToBooleanListFails() {
        this.convertToCollectionFails(
            null
        );
    }

    @Test
    public final void testConvertEmptyStringToBooleanList() {
        this.convertToCollectionAndCheck(
            ""
        );
    }

    @Test
    public final void testConvertSpacesToBooleanListFails() {
        this.convertToCollectionAndCheck(
            " "
        );
    }

    @Test
    public final void testConvertSpacesToBooleanListFails2() {
        this.convertToCollectionAndCheck(
            "  "
        );
    }

    @Test
    public final void testConvertUnclosedQuotesFails() {
        this.convertToCollectionFails(
            "\""
        );
    }

    @Test
    public final void testConvertUnclosedQuotesFails2() {
        this.convertToCollectionFails(
            "\" "
        );
    }

    final void convertToCollectionAndCheck(final CharSequence text,
                                           final ELEMENT... elements) {
        this.convertToCollectionAndCheck(
            text,
            this.createContext(),
            elements
        );
    }

    abstract void convertToCollectionAndCheck(final CharSequence text,
                                              final ConverterContext context,
                                              final ELEMENT... elements);

    final void convertToCollectionFails(final CharSequence text) {
        this.convertFails(
            text,
            this.collectionType()
        );
    }

    abstract Class<IMMUTABLE_COLLECTION> collectionType();
}
