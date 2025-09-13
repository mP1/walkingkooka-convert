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
import walkingkooka.math.NumberList;

/**
 * A Converter that handles converting a string such as a CSV with number literals to a {@link NumberList}. Padding
 * spaces are ignored. Each token between separators is trimmed of space and converted to {@link Number}.
 * <pre>
 * "1:58:59,2:00" ->
 * Lists.of(
 *   Number.of(1, 58, 59)
 *   Number.of(2, 0)
 * )
 * </pre>
 */
final class ConverterTextToListNumberList<C extends ConverterContext> extends ConverterTextToList<NumberList, Number, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTextToListNumberList<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterTextToListNumberList<?> INSTANCE = new ConverterTextToListNumberList<>();

    private ConverterTextToListNumberList() {
        super();
    }

    @Override
    Class<NumberList> listType() {
        return NumberList.class;
    }

    @Override
    Class<Number> elementType() {
        return Number.class;
    }

    @Override
    NumberList emptyList() {
        return NumberList.EMPTY;
    }
}
