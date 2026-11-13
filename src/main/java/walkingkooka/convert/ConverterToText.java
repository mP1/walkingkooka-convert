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
import walkingkooka.EmptyTextException;
import walkingkooka.InvalidTextLengthException;
import walkingkooka.text.HasText;
import walkingkooka.text.HasTextWithSeparator;

/**
 * A {@link Converter} that converts values of the following types:
 * <ul>
 * <li>{@link Character}</li>
 * <li>{@link CharSequence}</li>
 * <li>{@link HasText}</li>
 * <li>{@link HasTextWithSeparator}</li>
 * </ul>
 * into
 * <ul>
 * <li>{@link Character}</li>
 * <li>{@link CharSequence}</li>
 * <li>{@link String}</li>
 * </ul>
 */
final class ConverterToText<C extends ConverterContext> implements TryingShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterToText<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterToText<?> INSTANCE = new ConverterToText<>();

    /**
     * Private to stop sub classing.
     */
    private ConverterToText() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return (
            null == value ||
            value instanceof Character ||
            value instanceof CharSequence ||
            value instanceof HasText
        ) &&
            (
                Character.class == type ||
                CharSequence.class == type ||
                    String.class == type
            );
    }

    @Override
    public Object tryConvertOrFail(final Object value,
                                   final Class<?> type,
                                   final C context) {
        final Object textOrCharacter;

        if (null == value) {
            textOrCharacter = value;
        } else {
            if (value instanceof Character) {
                textOrCharacter = Character.class == type ?
                    value :
                    ((Character) value)
                        .toString();
            } else {
                final CharSequence text;

                if (value instanceof HasTextWithSeparator) {
                    final HasTextWithSeparator hasTextWithSeparator = (HasTextWithSeparator) value;
                    text = hasTextWithSeparator.textWithSeparator(
                        String.valueOf(
                            context.valueSeparator()
                        )
                    );
                } else {
                    if (value instanceof HasText) {
                        text = ((HasText) value)
                            .text();
                    } else {
                        if (value instanceof CharSequence) {
                            text = ((CharSequence) value);
                        } else {
                            text = null;
                        }
                    }
                }

                if (Character.class == type) {
                    // extract the first character or complain

                    final int length = text.length();
                    switch (length) {
                        case 0:
                            throw new EmptyTextException("Empty text");
                        case 1:
                            break;
                        default:
                            throw new InvalidTextLengthException(
                                "value",
                                text.toString(),
                                1,
                                1
                            );
                    }

                    textOrCharacter = text.charAt(0);
                } else {
                    textOrCharacter = CharSequence.class == type ?
                        text :
                        text.toString();
                }
            }
        }

        return textOrCharacter;
    }

    @Override
    public String toString() {
        return "to Text";
    }
}
