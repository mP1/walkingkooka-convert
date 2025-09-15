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
import walkingkooka.Either;
import walkingkooka.collect.list.BooleanList;
import walkingkooka.collect.list.CsvStringList;
import walkingkooka.collect.list.ImmutableList;
import walkingkooka.collect.list.StringList;
import walkingkooka.datetime.LocalDateList;
import walkingkooka.datetime.LocalDateTimeList;
import walkingkooka.datetime.LocalTimeList;
import walkingkooka.math.NumberList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

/**
 * A Converter that tries to convert {@link List} to other {@link java.util.List} types such as {@link walkingkooka.collect.list.StringList}.
 */
final class ConverterCollectionToList<C extends ConverterContext> implements ShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterCollectionToList<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterCollectionToList<?> INSTANCE = new ConverterCollectionToList<>();

    /**
     * Private to stop sub classing.
     */
    private ConverterCollectionToList() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return (null == value ||
            value instanceof Collection) &&
            (
                List.class == type ||
                    BooleanList.class == type ||
                    LocalDateList.class == type ||
                    LocalDateTimeList.class == type ||
                    LocalTimeList.class == type ||
                    NumberList.class == type ||
                    StringList.class == type ||
                    CsvStringList.class == type
            );
    }

    @Override
    public <T> Either<T, String> doConvert(final Object collection,
                                           final Class<T> type,
                                           final C context) {
        Object result = null;

        if (null != collection) {
            if (List.class == type || type == collection.getClass()) {
                result = collection;
            } else {
                ImmutableList<?> empty = null;
                Class<?> elementType;

                if (BooleanList.class == type) {
                    empty = BooleanList.EMPTY;
                    elementType = Boolean.class;
                } else {
                    if (LocalDateList.class == type) {
                        empty = LocalDateList.EMPTY;
                        elementType = LocalDate.class;
                    } else {
                        if (LocalDateTimeList.class == type) {
                            empty = LocalDateTimeList.EMPTY;
                            elementType = LocalDateTime.class;
                        } else {
                            if (LocalTimeList.class == type) {
                                empty = LocalTimeList.EMPTY;
                                elementType = LocalTime.class;
                            } else {
                                if (NumberList.class == type) {
                                    empty = NumberList.EMPTY;
                                    elementType = Number.class;
                                } else {
                                    if (StringList.class == type) {
                                        empty = StringList.EMPTY;
                                        elementType = String.class;
                                    } else {
                                        if (CsvStringList.class == type) {
                                            empty = CsvStringList.EMPTY;
                                            elementType = String.class;
                                        } else {
                                            throw new IllegalArgumentException("Unknown type: " + type);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                for (final Object element : (Collection<?>) collection) {
                    empty = empty.concat(
                        Cast.to(
                            context.convertOrFail(
                                element,
                                elementType
                            )
                        )
                    );
                }

                result = empty;
            }
        }

        return this.successfulConversion(
            Cast.to(result),
            type
        );
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return "Collection to List";
    }
}