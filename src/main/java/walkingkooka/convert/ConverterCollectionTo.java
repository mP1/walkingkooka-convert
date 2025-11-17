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

import java.util.Collection;

/**
 * A {@link Converter} that handles {@link java.util.Collection} with zero or one element. Collections with more elements
 * will fail conversion.
 */
final class ConverterCollectionTo<C extends ConverterContext> implements ShortCircuitingConverter<C> {

    /**
     * Instance
     */
    static <C extends ConverterContext> ConverterCollectionTo<C> instance() {
        return Cast.to(INSTANCE);
    }

    /**
     * Singleton
     */
    private final static ConverterCollectionTo<?> INSTANCE = new ConverterCollectionTo<>();

    private ConverterCollectionTo() {
        super();
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return null == value ||
            value instanceof Collection &&
                ((Collection<?>) value).size() <= 1;
    }

    @Override
    public <T> Either<T, String> doConvert(final Object value,
                                           final Class<T> type,
                                           final C context) {
        Object element = null;

        if (null != value) {
            final Collection<?> collection = (Collection<?>) value;
            element = collection.isEmpty() ?
                null :
                collection.iterator()
                    .next();
        }

        return context.convert(
            element,
            type
        );
    }

    // Object...........................................................................................................

    @Override
    public String toString() {
        return Collection.class.getSimpleName() + " to";
    }
}
