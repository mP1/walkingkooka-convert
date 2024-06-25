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
 * A {@link Converter} that may be used to convert values to a {@link Character} or {@link String}, possibly
 * using the provided {@link Converter} where necessary.
 * <br>
 * Examples of handled conversions include:
 * <ul>
 *     <li>{@link Character} to {@link String}</li>
 *     <li>{@link String} to {@link String}</li>
 *     <li>any type to {@link String} using the {@link Converter}</li>
 *     <li>any type to {@link String} using the {@link Converter} then to {@link Character}</li>
 *     <li>null to {@link String} using the {@link Converter}</li>
 *     <li>null to {@link String} using the {@link Converter} then to {@link Character}</li>
 * </ul>
 */
final class ConverterToStringOrCharacter<C extends ConverterContext> implements Converter<C> {

    /**
     * Factory that creates a {@link ConverterToStringOrCharacter} with the given {@link Converter}.
     */
    static <C extends ConverterContext> ConverterToStringOrCharacter<C> with(final Converter<C> converter) {
        Objects.requireNonNull(converter, "converter");

        ConverterToStringOrCharacter<C> result;

        if (converter instanceof ConverterToStringOrCharacter) {
            result = Cast.to(converter);
        } else {
            result = new ConverterToStringOrCharacter<>(converter);
        }

        return result;
    }

    private ConverterToStringOrCharacter(final Converter<C> converter) {
        super();
        this.converter = converter;
        this.stringToCharacter = Converters.stringToCharacter();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        final Converter<C> converter = this.converter;

        return (type == Character.class || type == String.class) &&
                (
                        (null == value || value instanceof Character || value instanceof String) ||
                                converter.canConvert(
                                        value,
                                        String.class,
                                        context
                                )
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
                this.convertCharacterOrString(
                        value,
                        type,
                        context
                ) :
                this.failConversion(value, type);
    }

    private <T> Either<T, String> convertCharacterOrString(final Object value,
                                                           final Class<T> type,
                                                           final C context) {
        return type == Character.class ?
                this.convertCharacter(value, type, context) :
                this.convertString(value, type, context);
    }

    private <T> Either<T, String> convertCharacter(final Object value,
                                                   final Class<T> type,
                                                   final C context) {
        return Cast.to(
                value instanceof Character ?
                        this.successfulConversion(value, type) : // already Character (aka Character -> Character):
                        value instanceof String ?
                                this.convertStringToCharacter(value, value.toString(), context) : //
                                this.convertStringThenCharacter(value, context) // convert the String -> Character
        );
    }

    private Either<Character, String> convertStringThenCharacter(final Object value,
                                                                 final C context) {
        final Either<String, String> string =
                this.converter.convert(
                        value,
                        String.class,
                        context
                );

        return string.isLeft() ?
                convertStringToCharacter(value, string.leftValue(), context) :
                this.failConversion(value, Character.class);
    }

    /**
     * Attempts to convert the given {@link String} to a {@link Character}.
     */
    private <T> Either<T, String> convertStringToCharacter(final Object value,
                                                           final String stringValue,
                                                           final C context) {
        final Either<Character, String> character = this.stringToCharacter.convert(
                stringValue,
                Character.class,
                context
        );

        return Cast.to(
                character.isLeft() ?
                        character :
                        this.failConversion(value, Character.class)
        );
    }

    /**
     * Handles converting a {@link String} to a {@link Character}.
     */
    private final Converter<C> stringToCharacter;

    /**
     * Convert the given value to a {@link String} and returns that result.
     */
    private <T> Either<T, String> convertString(final Object value,
                                                final Class<T> type,
                                                final C context) {
        return value instanceof String ?
                this.successfulConversion(
                        value, // value is already a String
                        type
                ) :
                this.converter.convert(
                        value,
                        type,
                        context
                );
    }

    /**
     * The {@link Converter} that will convert a {@link String} to the target type.
     */
    private final Converter<C> converter;

    // Object..........................................................................................................

    @Override
    public String toString() {
        return this.converter + " to Character | String";
    }
}
