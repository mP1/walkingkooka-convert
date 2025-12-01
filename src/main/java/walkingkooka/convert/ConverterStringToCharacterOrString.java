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
final class ConverterStringToCharacterOrString<C extends ConverterContext> implements TryingShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterStringToCharacterOrString<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterStringToCharacterOrString<?> INSTANCE = new ConverterStringToCharacterOrString<>();

    /**
     * Private to stop sub classing.
     */
    private ConverterStringToCharacterOrString() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        boolean canConvert = false;

        if (Character.class == type || String.class == type) {
            canConvert = null == value;

            if (false == canConvert) {
                if (value instanceof String) {
                    canConvert = Character.class == type ?
                        ((String) value).length() == 1 :
                        true
                    ;
                }
            }
        }

        return canConvert;
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
