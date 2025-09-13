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
import walkingkooka.datetime.LocalDateTimeList;

import java.time.LocalDateTime;

/**
 * A Converter that handles converting a string such as a CSV with date literals to a {@link LocalDateTimeList}. Padding
 * spaces are ignored. Each token between separators is trimmed of space and converted to {@link LocalDateTime}.
 * <pre>
 * "31/12/1999 1:11:58,1/1/2000 2:22:59" ->
 * Lists.of(
 *   LocalDateTime.of(1999, 12, 31, 1:11:58)
 *   LocalDateTime.of(2000, 1, 1, 2:22:59)
 * )
 * </pre>
 */
final class ConverterTextToListLocalDateTimeList<C extends ConverterContext> extends ConverterTextToList<LocalDateTimeList, LocalDateTime, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTextToListLocalDateTimeList<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterTextToListLocalDateTimeList<?> INSTANCE = new ConverterTextToListLocalDateTimeList<>();

    private ConverterTextToListLocalDateTimeList() {
        super();
    }

    @Override
    Class<LocalDateTimeList> listType() {
        return LocalDateTimeList.class;
    }

    @Override
    Class<LocalDateTime> elementType() {
        return LocalDateTime.class;
    }

    @Override
    LocalDateTimeList emptyList() {
        return LocalDateTimeList.EMPTY;
    }
}
