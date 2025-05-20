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
 * A {@link TemplatedConverter} that automatically converts {@link Character}, {@link CharSequence}, {@link walkingkooka.text.HasText} or {@link String}
 * to an intermediate {@link String} and then invoking a {@link #parseText(String, Class, ConverterContext)} to complete
 * the process of converting a text like value into a target value.
 */
public interface ToTextTemplatedConverter<C extends ConverterContext> extends TemplatedConverter<C> {

    @Override
    default boolean canConvert(final Object value,
                               final Class<?> type,
                               final C context) {
        // test isType is quicker so do first
        return this.isTargetType(
                value,
                type,
                context
        ) &&
                context.canConvert(
                        value,
                        String.class
                );
    }

    boolean isTargetType(final Object value,
                         final Class<?> type,
                         final C context);

    @Override
    default Object tryConvertOrFail(final Object value,
                                    final Class<?> type,
                                    final C context) {
        return this.parseText(
                context.convertOrFail(
                        value,
                        String.class
                ),
                type,
                context
        );
    }

    Object parseText(final String value,
                     final Class<?> type,
                     final C context);
}
