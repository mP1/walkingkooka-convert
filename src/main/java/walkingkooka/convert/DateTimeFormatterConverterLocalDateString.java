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

import walkingkooka.datetime.DateTimeContext;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * A {@link Converter} that formats a {@link LocalDate} into a {@link String}
 */
final class DateTimeFormatterConverterLocalDateString<C extends ConverterContext> extends DateTimeFormatterConverter<LocalDate, String, C> {

    static <C extends ConverterContext> DateTimeFormatterConverterLocalDateString<C> with(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return new DateTimeFormatterConverterLocalDateString<>(formatter);
    }

    private DateTimeFormatterConverterLocalDateString(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        super(formatter);
    }

    @Override
    boolean canConvertNonNull(final Object value,
                              final Class<?> type,
                              final C context) {
        return value instanceof LocalDate;
    }

    @Override
    boolean canConvertType(final Class<?> type) {
        return String.class == type;
    }

    @Override
    String parseOrFormat(final LocalDate value,
                         final DateTimeFormatter formatter) throws IllegalArgumentException {
        return value.format(formatter);
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return "LocalDate->String";
    }
}
