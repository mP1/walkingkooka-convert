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

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DecimalStyle;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Function;

/**
 * A {@link Converter} which uses a {@link DateTimeFormatter} in some part of the conversion process.
 */
abstract class ConverterDateTimeFormatter<S, D, C extends ConverterContext> implements TryingShortCircuitingConverter<C> {

    /**
     * Package private to limit sub classing.
     */
    ConverterDateTimeFormatter(final Function<DateTimeContext, DateTimeFormatter> formatter) {
        Objects.requireNonNull(formatter, "formatter");
        this.formatter = formatter;
    }

    @Override
    public final boolean canConvert(final Object value,
                                    final Class<?> type,
                                    final C context) {
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(context, "context");

        return (
            null == value ||
                this.canConvertNonNull(
                    value,
                    type,
                    context
                )
        ) &&
            this.canConvertType(type);
    }

    abstract boolean canConvertNonNull(final Object value,
                                       final Class<?> type,
                                       final C context);

    abstract boolean canConvertType(final Class<?> type);

    @Override
    public Object tryConvertOrFail(final Object value,
                                   final Class<?> type,
                                   final C context) {
        Object result = null;

        if(null != value) {
            final Locale locale = context.locale();
            final int twoDigitYear = context.twoDigitYear();

            ConverterDateTimeFormatterCache cache = this.cache;
            DateTimeFormatter dateTimeFormatter;
            if (null == cache) {
                dateTimeFormatter = this.formatter.apply(context);
                this.cache = ConverterDateTimeFormatterCache.with(
                    locale,
                    twoDigitYear,
                    dateTimeFormatter
                );
            } else {
                if (false == cache.locale.equals(locale) || cache.twoDigitYear != twoDigitYear) {
                    dateTimeFormatter = this.formatter.apply(context)
                        .withDecimalStyle(DecimalStyle.of(locale)
                            .withPositiveSign(context.positiveSign())
                            .withNegativeSign(context.negativeSign())
                            .withDecimalSeparator(context.decimalSeparator()));
                    cache = ConverterDateTimeFormatterCache.with(
                        locale,
                        twoDigitYear,
                        dateTimeFormatter
                    );
                    this.cache = cache;
                }

                dateTimeFormatter = cache.formatter;
            }

            result = this.parseOrFormat(
                (S)value,
                dateTimeFormatter
            );
        }

        return result;
    }

    /**
     * A factory which returns a {@link DateTimeFormatter} on demand using the {@link DateTimeContext}.
     */
    final Function<DateTimeContext, DateTimeFormatter> formatter;

    private transient ConverterDateTimeFormatterCache cache;

    /**
     * Sub classes should parse or format the value using the {@link DateTimeContext} aware {@link DateTimeFormatter}.
     */
    abstract D parseOrFormat(final S value,
                             final DateTimeFormatter formatter) throws IllegalArgumentException, DateTimeException;
}
