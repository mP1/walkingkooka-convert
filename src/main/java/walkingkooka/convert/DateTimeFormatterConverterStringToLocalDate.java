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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Function;

/**
 * A {@link Converter} that parses a {@link String} into a {@link LocalTime}.
 */
final class DateTimeFormatterConverterStringToLocalDate<C extends ConverterContext> extends DateTimeFormatterConverter<String, LocalDate, C> {

    static <C extends ConverterContext> DateTimeFormatterConverterStringToLocalDate<C> with(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        return new DateTimeFormatterConverterStringToLocalDate<>(formatter);
    }

    /**
     * Private ctor use static factory
     */
    private DateTimeFormatterConverterStringToLocalDate(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        super(formatter);
    }

    @Override
    boolean canConvertNonNull(final Object value,
                              final Class<?> type,
                              final C context) {
        return value instanceof String;
    }

    @Override
    boolean canConvertType(final Class<?> type) {
        return LocalDate.class == type;
    }

    @Override
    LocalDate parseOrFormat(final String text,
                            final DateTimeFormatter formatter) throws DateTimeParseException {
        return LocalDate.parse(text, formatter);
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return "String to LocalDate";
    }
}
