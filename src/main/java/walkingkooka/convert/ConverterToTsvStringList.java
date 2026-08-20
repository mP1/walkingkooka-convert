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

import walkingkooka.Either;
import walkingkooka.collect.list.HasTsvStringList;
import walkingkooka.collect.list.TsvStringList;

/**
 * A {@link Converter} that supports converting {@link HasTsvStringList} to {@link TsvStringList}.
 */
final class ConverterToTsvStringList<C extends ConverterContext> implements ShortCircuitingConverter<C> {

    /**
     * Type safe getter
     */
    static <C extends ConverterContext> ConverterToTsvStringList<C> instance() {
        return INSTANCE;
    }

    /**
     * Singleton
     */
    final static ConverterToTsvStringList INSTANCE = new ConverterToTsvStringList<>();

    private ConverterToTsvStringList() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return value instanceof HasTsvStringList &&
            TsvStringList.class == type;
    }

    @Override
    public <T> Either<T, String> doConvert(final Object value,
                                           final Class<T> type,
                                           final C context) {
        return this.successfulConversion(
            ((HasTsvStringList) value).tsvStringList(),
            type
        );
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return "to " + TsvStringList.class.getSimpleName();
    }
}
