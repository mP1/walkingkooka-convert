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
import walkingkooka.text.HasMultiLineText;
import walkingkooka.text.MultiLineText;

/**
 * A Converter that converts any {@link HasMultiLineText} into a {@link MultiLineText}.
 */
final class ConverterToMultiLineText<C extends ConverterContext> implements TryingShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterToMultiLineText<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterToMultiLineText<?> INSTANCE = new ConverterToMultiLineText<>();

    /**
     * Private to stop sub classing.
     */
    private ConverterToMultiLineText() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return MultiLineText.class == type &&
            (
                null == value ||
                value instanceof HasMultiLineText ||
                    context.canConvert(value, String.class)
            );
    }

    @Override
    public Object tryConvertOrFail(final Object value,
                                   final Class<?> type,
                                   final C context) {
        return null == value ?
            null :
            value instanceof HasMultiLineText ?
                ((HasMultiLineText) value).multiLineText(context) :
                // when not HasMultiLineText convert to String and create a MultiLineText
                MultiLineText.with(
                    context.convertOrFail(
                        value,
                        String.class
                    )
                );
    }

    @Override
    public String toString() {
        return "to " + MultiLineText.class.getSimpleName();
    }
}
