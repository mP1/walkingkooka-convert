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
 * Mixing testing interface for {@link ConverterLike}
 */
public interface ConverterLikeTesting2<C extends ConverterLike> extends ConverterLikeTesting {

    // canConvert.......................................................................................................

    default void canConvertAndCheck(final Object value,
                                    final Class<?> type,
                                    final boolean expected) {
        this.canConvertAndCheck(
            this.createConverterLike(),
            value,
            type,
            expected
        );
    }

    // convert..........................................................................................................

    default <T> T convertAndCheck(final Object value,
                                  final Class<T> target,
                                  final T expected) {
        return this.convertAndCheck(
            this.createConverterLike(),
            value,
            target,
            expected
        );
    }

    default void convertFails(final Object value,
                              final Class<?> type) {
        this.convertFails(
            this.createConverterLike(),
            value,
            type
        );
    }

    default <T> T convertOrFailAndCheck(final Object value,
                                        final Class<T> target,
                                        final T expected) {
        return this.convertOrFailAndCheck(
            this.createConverterLike(),
            value,
            target,
            expected
        );
    }

    /**
     * Factory that creates a {@link ConverterLike}.
     */
    C createConverterLike();
}
