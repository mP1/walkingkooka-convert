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

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;
import walkingkooka.Either;
import walkingkooka.collect.list.Lists;
import walkingkooka.predicate.Predicates;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ConverterCollectionTest extends ConverterTestCase2<ConverterCollection<ConverterContext>> {

    @Override
    public void testTypeNaming() {
        throw new UnsupportedOperationException();
    }

    @Test
    public void testWithNullConvertersFails() {
        assertThrows(NullPointerException.class, () -> ConverterCollection.with(null));
    }

    @Test
    public void testWithZeroConvertersFails() {
        assertThrows(IllegalArgumentException.class, () -> ConverterCollection.with(Lists.empty()));
    }

    @Test
    public void testWithOneConverter() {
        final Converter<ConverterContext> only = Converters.objectString();
        assertSame(only, ConverterCollection.with(Lists.of(only)));
    }

    @Test
    public void testFirst() {
        this.convertAndCheck(Boolean.TRUE.toString(), Boolean.class, Boolean.TRUE);
    }

    @Test
    public void testLast() {
        this.convertAndCheck(1.0, Long.class, 1L);
    }

    @Test
    public void testUnhandledTargetType() {
        this.convertFails("Cant convert to Void", Void.class);
    }

    @Test
    public void testSkipsFailed() {
        this.convertAndCheck(ConverterCollection.with(Lists.of(new Converter<ConverterContext>() {
                                                                   @Override
                                                                   public boolean canConvert(final Object value,
                                                                                             final Class<?> type,
                                                                                             final ConverterContext context) {
                                                                       return true;
                                                                   }

                                                                   @Override
                                                                   public <T> Either<T, String> convert(final Object value,
                                                                                                        final Class<T> type,
                                                                                                        final ConverterContext context) {
                                                                       return Either.right("failed!");
                                                                   }
                                                               },
                Converters.truthyNumberBoolean())),
                1,
                Boolean.class,
                true);
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createConverter(), "String->Boolean | Number->Number");
    }

    @Override
    public ConverterCollection<ConverterContext> createConverter() {
        return Cast.to(ConverterCollection.with(Lists.of(booleanToString().setToString("String->Boolean"), Converters.numberNumber())));
    }

    private Converter<ConverterContext> booleanToString() {
        return Converters.<String, Boolean>function(t-> t instanceof String, Predicates.is(Boolean.class), Boolean::valueOf);
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    @Override
    public Class<ConverterCollection<ConverterContext>> type() {
        return Cast.to(ConverterCollection.class);
    }
}
