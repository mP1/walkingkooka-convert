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

import walkingkooka.Binary;
import walkingkooka.Cast;

/**
 * A converter that supports converting text into a {@link Binary} using {@link Binary} using the {@link ConverterContext#charset()}.
 */
final class ConverterTextToBinary<C extends ConverterContext> extends ConverterTextTo<Binary, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTextToBinary<C> instance() {
        return Cast.to(INSTANCE);
    }

    private final static ConverterTextToBinary INSTANCE = new ConverterTextToBinary<>();

    private ConverterTextToBinary() {
        super();
    }

    @Override
    Class<Binary> targetType() {
        return Binary.class;
    }

    @Override
    public Binary parseText(final String text,
                                 final Class<?> type,
                                 final C context) {
        return Binary.with(
            text.getBytes(context.charset())
        );
    }
}
