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

public final class BooleanTrueFalseConverterTest extends ConverterTestCase2<BooleanTrueFalseConverter<String, ConverterContext>> {

    private final static Predicate<Object> SOURCE_TESTER = Predicates.customToString((t) -> t instanceof Integer, "Integer");
    private final static Predicate<Class<?>> TARGET_TESTER = Predicates.customToString((t) -> t == String.class, "String");
    private final static Predicate<Object> TRUE_VALUE = Predicates.is(1);
    private final static String TRUE_ANSWER = "true!!";
    private final static String FALSE_ANSWER = "false!!";

    @Test
    public void testWithNullSourceFails() {
        assertThrows(NullPointerException.class, () -> BooleanTrueFalseConverter.with(null, TARGET_TESTER, TRUE_VALUE, TRUE_ANSWER, FALSE_ANSWER));
    }

    @Test
    public void testWithNullTargetTypeFails() {
        assertThrows(NullPointerException.class, () -> BooleanTrueFalseConverter.with(SOURCE_TESTER, null, TRUE_VALUE, TRUE_ANSWER, FALSE_ANSWER));
    }
    
    @Test
    public void testWithNullFalseValueFails() {
        assertThrows(NullPointerException.class, () -> BooleanTrueFalseConverter.with(SOURCE_TESTER, TARGET_TESTER, null, TRUE_ANSWER, FALSE_ANSWER));
    }

    @Test
    public void testNull() {
        this.convertAndCheck2(null, FALSE_ANSWER);
    }

    @Test
    public void testTrue() {
        this.convertAndCheck2(1, TRUE_ANSWER);
    }

    @Test
    public void testTrueNullAnswer() {
        this.convertAndCheck(
                BooleanTrueFalseConverter.with(SOURCE_TESTER, TARGET_TESTER, TRUE_VALUE, null, FALSE_ANSWER),
                1,
                String.class,
                null
        );
    }

    @Test
    public void testFalse() {
        this.convertAndCheck2(2, FALSE_ANSWER);
    }

    @Test
    public void testFalseNullAnswer() {
        this.convertAndCheck(
                BooleanTrueFalseConverter.with(SOURCE_TESTER, TARGET_TESTER, TRUE_VALUE, TRUE_ANSWER, null),
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
    public BooleanTrueFalseConverter<String, ConverterContext> createConverter() {
        return BooleanTrueFalseConverter.with(SOURCE_TESTER, TARGET_TESTER, TRUE_VALUE, TRUE_ANSWER, FALSE_ANSWER);
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    void convertAndCheck2(final Object value,
                          final String expected) {
        this.convertAndCheck(
                this.createConverter(),
                value,
                String.class,
                expected
        );
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<BooleanTrueFalseConverter<String, ConverterContext>> type() {
        return Cast.to(BooleanTrueFalseConverter.class);
    }
}
