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
import walkingkooka.predicate.Predicates;

import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ToBooleanConverterTest extends ConverterTestCase2<ToBooleanConverter<String, ConverterContext>> {

    private final static Predicate<Object> SOURCE_TESTER = Predicates.customToString((t) -> t instanceof Integer, "Integer");
    private final static Predicate<Class<?>> TARGET_TESTER = Predicates.customToString((t) -> t == String.class, "String");
    private final static Predicate<Object> TRUE_VALUE = Predicates.is(1);
    private final static String TRUE_ANSWER = "true!!";
    private final static String FALSE_ANSWER = "false!!";

    @Test
    public void testWithNullSourceFails() {
        assertThrows(NullPointerException.class, () -> ToBooleanConverter.with(null, TARGET_TESTER, TRUE_VALUE, TRUE_ANSWER, FALSE_ANSWER));
    }

    @Test
    public void testWithNullTargetTypeFails() {
        assertThrows(NullPointerException.class, () -> ToBooleanConverter.with(SOURCE_TESTER, null, TRUE_VALUE, TRUE_ANSWER, FALSE_ANSWER));
    }
    
    @Test
    public void testWithNullFalseValueFails() {
        assertThrows(NullPointerException.class, () -> ToBooleanConverter.with(SOURCE_TESTER, TARGET_TESTER, null, TRUE_ANSWER, FALSE_ANSWER));
    }

    @Test
    public void testNull() {
        this.convertAndCheck(
                null,
                FALSE_ANSWER
        );
    }

    @Test
    public void testTrue() {
        this.convertAndCheck(
                1,
                TRUE_ANSWER
        );
    }

    @Test
    public void testTrueNullAnswer() {
        this.convertAndCheck(
                ToBooleanConverter.with(SOURCE_TESTER, TARGET_TESTER, TRUE_VALUE, null, FALSE_ANSWER),
                1,
                String.class,
                null
        );
    }

    @Test
    public void testFalse() {
        this.convertAndCheck(
                2,
                FALSE_ANSWER
        );
    }

    @Test
    public void testFalseNullAnswer() {
        this.convertAndCheck(
                ToBooleanConverter.with(SOURCE_TESTER, TARGET_TESTER, TRUE_VALUE, TRUE_ANSWER, null),
                2,
                String.class,
                null
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createConverter(),
                "Integer->String");
    }

    @Override
    public ToBooleanConverter<String, ConverterContext> createConverter() {
        return ToBooleanConverter.with(SOURCE_TESTER, TARGET_TESTER, TRUE_VALUE, TRUE_ANSWER, FALSE_ANSWER);
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<ToBooleanConverter<String, ConverterContext>> type() {
        return Cast.to(ToBooleanConverter.class);
    }
}
