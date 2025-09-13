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
import walkingkooka.datetime.LocalTimeList;

import java.time.LocalTime;

/**
 * A Converter that handles converting a string such as a CSV with time literals to a {@link LocalTimeList}. Padding
 * spaces are ignored. Each token between separators is trimmed of space and converted to {@link LocalTime}.
 * <pre>
 * "1:58:59,2:00" ->
 * Lists.of(
 *   LocalTime.of(1, 58, 59)
 *   LocalTime.of(2, 0)
 * )
 * </pre>
 */
final class ConverterTextToListLocalTimeList<C extends ConverterContext> extends ConverterTextToList<LocalTimeList, LocalTime, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTextToListLocalTimeList<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterTextToListLocalTimeList<?> INSTANCE = new ConverterTextToListLocalTimeList<>();

    private ConverterTextToListLocalTimeList() {
        super();
    }

    @Override
    Class<LocalTimeList> listType() {
        return LocalTimeList.class;
    }

    @Override
    Class<LocalTime> elementType() {
        return LocalTime.class;
    }

    @Override
    LocalTimeList emptyList() {
        return LocalTimeList.EMPTY;
    }
}
