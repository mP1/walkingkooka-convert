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
import walkingkooka.datetime.LocalDateList;

import java.time.LocalDate;

/**
 * A Converter that handles converting a string such as a CSV with date literals to a {@link LocalDateList}. Padding
 * spaces are ignored. Each token between separators is trimmed of space and converted to {@link LocalDate}.
 * <pre>
 * "31/12/1999,1/1/2000" ->
 * Lists.of(
 *   LocalDate.of(1999, 12, 31)
 *   LocalDate.of(2000, 1, 1)
 * )
 * </pre>
 */
final class ConverterTextToListLocalDateList<C extends ConverterContext> extends ConverterTextToList<LocalDateList, LocalDate, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTextToListLocalDateList<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterTextToListLocalDateList<?> INSTANCE = new ConverterTextToListLocalDateList<>();

    private ConverterTextToListLocalDateList() {
        super();
    }

    @Override
    Class<LocalDateList> listType() {
        return LocalDateList.class;
    }

    @Override
    Class<LocalDate> elementType() {
        return LocalDate.class;
    }

    @Override
    LocalDateList emptyList() {
        return LocalDateList.EMPTY;
    }
}
