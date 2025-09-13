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
import walkingkooka.collect.list.BooleanList;

/**
 * A Converter that handles converting a string such as a CSV with boolean literals to a {@link BooleanList}. Padding
 * spaces are ignored. Each token between separators is trimmed of space and converted to {@link Boolean}.
 * <pre>
 * "TRUE,FALSE" ->
 * Lists.of(
 *   Boolean.TRUE,
 *   Boolean.FALSE
 * )
 * </pre>
 */
final class ConverterTextToListBooleanList<C extends ConverterContext> extends ConverterTextToList<BooleanList, Boolean, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTextToListBooleanList<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterTextToListBooleanList<?> INSTANCE = new ConverterTextToListBooleanList<>();

    private ConverterTextToListBooleanList() {
        super();
    }

    @Override
    Class<BooleanList> listType() {
        return BooleanList.class;
    }

    @Override
    Class<Boolean> elementType() {
        return Boolean.class;
    }

    @Override
    BooleanList emptyList() {
        return BooleanList.EMPTY;
    }
}
