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

import java.time.LocalDate;

/**
 * Converts a {@link LocalDate} into another type.
 */
abstract class ConverterTemporalLocalDate<D, C extends ConverterContext> extends ConverterTemporal<LocalDate, D, C> {

    /**
     * Package private to limit sub classing.
     */
    ConverterTemporalLocalDate() {
        super();
    }

    @Override //
    final boolean canConvertNonNull(final Object value,
                                    final Class<?> type,
                                    final C context) {
        return value instanceof LocalDate;
    }

    @Override //
    final Class<LocalDate> sourceType() {
        return LocalDate.class;
    }

    @Override
    boolean canConvertType(final Class<?> type) {
        return this.targetType() == type;
    }
}
