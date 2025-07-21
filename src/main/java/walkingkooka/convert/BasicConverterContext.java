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
import walkingkooka.datetime.DateTimeContext;
import walkingkooka.datetime.DateTimeContextDelegator;
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.math.DecimalNumberContextDelegator;

import java.math.MathContext;
import java.util.Locale;
import java.util.Objects;

/**
 * An adaptor for {@link DecimalNumberContext} to {@link ConverterContext}.
 */
final class BasicConverterContext implements ConverterContext,
    DateTimeContextDelegator,
    DecimalNumberContextDelegator {

    /**
     * Creates a new {@link BasicConverterContext}.
     */
    static BasicConverterContext with(final long dateOffset,
                                      final Converter<ConverterContext> converter,
                                      final DateTimeContext dateTimeContext,
                                      final DecimalNumberContext decimalNumberContext) {
        Objects.requireNonNull(converter, "converter");
        Objects.requireNonNull(dateTimeContext, "dateTimeContext");
        Objects.requireNonNull(decimalNumberContext, "decimalNumberContext");

        return new BasicConverterContext(
            dateOffset,
            converter,
            dateTimeContext,
            decimalNumberContext
        );
    }

    /**
     * Private ctor use factory
     */
    private BasicConverterContext(final long dateOffset,
                                  final Converter<ConverterContext> converter,
                                  final DateTimeContext dateTimeContext,
                                  final DecimalNumberContext decimalNumberContext) {
        super();

        this.dateOffset = dateOffset;
        this.converter = converter;
        this.dateTimeContext = dateTimeContext;
        this.decimalNumberContext = decimalNumberContext;
    }

    @Override
    public long dateOffset() {
        return this.dateOffset;
    }

    private final long dateOffset;

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type) {
        return this.converter.canConvert(value, type, this);
    }

    @Override
    public <T> Either<T, String> convert(final Object value,
                                         final Class<T> target) {
        return this.converter.convert(value, target, this);
    }

    private final Converter<ConverterContext> converter;

    // DateTimeContext..................................................................................................

    @Override
    public DateTimeContext dateTimeContext() {
        return this.dateTimeContext;
    }

    private final DateTimeContext dateTimeContext;

    // DecimalNumberContext.............................................................................................

    @Override
    public Locale locale() {
        return this.decimalNumberContext.locale();
    }

    @Override
    public MathContext mathContext() {
        return this.decimalNumberContext.mathContext();
    }

    @Override
    public DecimalNumberContext decimalNumberContext() {
        return this.decimalNumberContext;
    }

    private final DecimalNumberContext decimalNumberContext;

    @Override
    public String toString() {
        return this.dateTimeContext + " " + this.decimalNumberContext;
    }
}
