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

import java.util.Objects;

/**
 * A {@link Converter} that converts any {@link Character} to a {@link String} before calling a given {@link Converter}
 * which is expected to convert the {@link Character} to the requested target type.
 */
final class ConverterCharacterStringConverter<C extends ConverterContext> implements Converter<C> {

    static <C extends ConverterContext> ConverterCharacterStringConverter<C> with(final Converter<C> converter) {
        Objects.requireNonNull(converter, "converter");

        ConverterCharacterStringConverter<C> result;

        if (converter instanceof ConverterCharacterStringConverter) {
            result = Cast.to(converter);
        } else {
            result = new ConverterCharacterStringConverter<>(converter);
        }

        return result;
    }

    private ConverterCharacterStringConverter(final Converter<C> converter) {
        super();
        this.converter = converter;
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return value instanceof Character &&
                this.converter.canConvert(
                        value.toString(),
                        type,
                        context
                );
    }

    @Override
    public <T> Either<T, String> convert(final Object value,
                                         final Class<T> type,
                                         final C context) {
        return this.canConvert(
                value,
                type,
                context
        ) ?
                this.convertString(
                        (Character) value,
                        type,
                        context
                ) :
                this.failConversion(value, type);
    }

    /**
     * Calls the wrapped {@link Converter} after converting the given {@link Character} to a {@link String}.
     */
    private <T> Either<T, String> convertString(final Character value,
                                                final Class<T> type,
                                                final C context) {
        return this.converter.convert(
                value.toString(),
                type,
                context
        );
    }

    /**
     * The {@link Converter} that will convert a {@link String} to the target type.
     */
    private final Converter<C> converter;

    @Override
    public String toString() {
        return "Character->" + this.converter;
    }
}
