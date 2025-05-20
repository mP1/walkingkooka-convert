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

/**
 * A Converter that supports converting String to Character if string is 1 char or a String..
 */
final class StringToCharacterOrStringConverter<C extends ConverterContext> implements TemplatedConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> StringToCharacterOrStringConverter<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static StringToCharacterOrStringConverter<?> INSTANCE = new StringToCharacterOrStringConverter<>();


    /**
     * Private to stop sub classing.
     */
    private StringToCharacterOrStringConverter() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return Character.class == type &&
                (null == value || value instanceof String && ((String) value).length() == 1) ||
                String.class == type;
    }

    @Override
    public Object tryConvertOrFail(final Object value,
                                   final Class<?> type,
                                   final C context) {
        return null == value || String.class == type ?
                value :
                value.toString().charAt(0);
    }

    @Override
    public String toString() {
        return "String to Character or String";
    }
}
