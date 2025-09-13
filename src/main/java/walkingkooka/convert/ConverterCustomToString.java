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
import walkingkooka.text.Whitespace;

import java.util.Objects;

/**
 * Wraps another {@link Converter} replacing or ignoring its {@link Converter#toString()} with the provided {@link String}.
 */
final class ConverterCustomToString<C extends ConverterContext> implements Converter<C> {

    static <C extends ConverterContext> Converter<C> wrap(final Converter<C> converter,
                                                          final String toString) {
        Objects.requireNonNull(converter, "converter");
        Whitespace.failIfNullOrEmptyOrWhitespace(toString, "toString");

        Converter<C> result;

        for (; ; ) {
            if (converter.toString().equals(toString)) {
                result = converter;
                break;
            }

            Converter<C> wrap = converter;
            if (converter instanceof ConverterCustomToString) {
                // unwrap then re-wrap the converter...
                final ConverterCustomToString<C> custom = Cast.to(wrap);
                wrap = custom.converter;
            }
            result = new ConverterCustomToString<>(wrap, toString);
            break;
        }

        return result;
    }

    private ConverterCustomToString(final Converter<C> converter, final String toString) {
        this.converter = converter;
        this.toString = toString;
    }


    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return this.converter.canConvert(value, type, context);
    }

    @Override
    public <T> Either<T, String> convert(final Object value,
                                         final Class<T> type,
                                         final C context) {
        return this.converter.convert(value, type, context);
    }

    // @VisibleForTesting
    final Converter<C> converter;

    // Object
    @Override
    public int hashCode() {
        return Objects.hash(this.converter, this.toString);
    }

    @Override
    public boolean equals(final Object other) {
        return this == other || other instanceof ConverterCustomToString && this.equals0(Cast.to(other));
    }

    private boolean equals0(final ConverterCustomToString<?> other) {
        return this.converter.equals(other.converter) &&
            this.toString.equals(other.toString);
    }

    @Override
    public String toString() {
        return this.toString;
    }

    // @VisibleForTesting
    final String toString;
}
