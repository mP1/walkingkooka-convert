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
import walkingkooka.collect.list.StringList;

/**
 * A Converter that handles converting a string such as a CSV with string literals to a {@link StringList}. Padding
 * spaces are ignored. Each token between separators is trimmed of space and converted to {@link String}.
 * <pre>
 * "a,bc,def" ->
 * Lists.of(
 *   "a",
 *   "bc",
 *   "def"
 * )
 * </pre>
 */
final class ConverterTextToListStringList<C extends ConverterContext> extends ConverterTextToList<StringList, String, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTextToListStringList<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterTextToListStringList<?> INSTANCE = new ConverterTextToListStringList<>();

    private ConverterTextToListStringList() {
        super();
    }

    @Override
    Class<StringList> listType() {
        return StringList.class;
    }

    @Override
    Class<String> elementType() {
        return String.class;
    }

    @Override
    StringList emptyList() {
        return StringList.EMPTY;
    }
}
