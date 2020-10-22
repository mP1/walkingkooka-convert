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
import walkingkooka.test.Testing;
import walkingkooka.text.CharSequences;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * A mixin interface with helpers to assist testing {@link ConvertOrFailFunction}.
 */
public interface ConvertOrFailFunctionTesting extends Testing {

    default <T> T convertOrFailAndCheck(final ConvertOrFailFunction converter,
                                        final Object value,
                                        final Class<T> target,
                                        final T expected) {
        final T convertedValue = converter.convertOrFail(value, target);
        checkEquals("Failed to convertOrFail " + CharSequences.quoteIfChars(value) + " (" + value.getClass().getName() + ")= to " + target.getName(), expected, convertedValue);
        return convertedValue;
    }

    default void checkEquals(final String message,
                             final Object expected,
                             final Object actual) {
        if (expected instanceof Comparable && expected.getClass().isInstance(actual)) {
            final Comparable<?> expectedComparable = Cast.to(expected);
            if (expectedComparable.compareTo(Cast.to(actual)) != 0) {
                assertEquals(expected, actual, message);
            }
        } else {
            assertEquals(expected, actual, message);
        }
    }

    default void convertOrFailFails(final ConvertOrFailFunction converter,
                                    final Object value,
                                    final Class<?> target) {
        assertThrows(ConversionException.class, () -> converter.convertOrFail(value, target));
    }
}
