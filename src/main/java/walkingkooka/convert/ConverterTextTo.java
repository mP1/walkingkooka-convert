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

/**
 * A {@link Converter} that handles converting text to another type
 */
abstract class ConverterTextTo<T, C extends ConverterContext> implements TextToTryingShortCircuitingConverter<C> {

    ConverterTextTo() {
        super();
    }

    @Override
    public boolean isTargetType(final Object value,
                                final Class<?> type,
                                final C context) {
        return this.targetType()  == type;
    }

    abstract Class<T> targetType();

    // Object...........................................................................................................

    @Override
    public final String toString() {
        return "text to " + this.targetType()
            .getSimpleName();
    }
}
