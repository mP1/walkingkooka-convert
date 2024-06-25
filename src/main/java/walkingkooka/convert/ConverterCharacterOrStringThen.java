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
 * A {@link Converter} that supports considering {@link Character} as equivalent to {@link String} before passing
 * the {@link String} to a wrapped converter for conversion.
 * <br>
 * This supports the following conversion requests.
 * <ul>
 *     <li>{@link String} to the types supported by the {@link Converter}</li>
 *     <li>{link Character} to {@link String} followed by the types supported by the wrapped {@link Converter}</li>
 * </ul>
 * <br>
 * Note converting {@link Character} or {@link String }to {@link String} is not supported.
 */
final class ConverterCharacterOrStringThen<C extends ConverterContext> implements Converter<C> {

    static <C extends ConverterContext> ConverterCharacterOrStringThen<C> with(final Converter<C> converter) {
        Objects.requireNonNull(converter, "converter");

        ConverterCharacterOrStringThen<C> result;

        if (converter instanceof ConverterCharacterOrStringThen) {
            result = Cast.to(converter);
        } else {
            result = new ConverterCharacterOrStringThen<>(converter);
        }

        return result;
    }

    private ConverterCharacterOrStringThen(final Converter<C> converter) {
        super();
        this.converter = converter;
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return this.converter.canConvert(
                value instanceof Character ? value.toString() : value,
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
                        null != value ? value.toString() : (String) value,
                        type,
                        context
                ) :
                this.failConversion(value, type);
    }

    /**
     * Calls the wrapped {@link Converter} with the given {@link String}.
     */
    private <T> Either<T, String> convertString(final String value,
                                                final Class<T> type,
                                                final C context) {
        return this.converter.convert(
                value,
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
        return "Character to " + this.converter;
    }
}
