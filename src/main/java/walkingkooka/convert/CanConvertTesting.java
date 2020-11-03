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

import org.opentest4j.AssertionFailedError;
import walkingkooka.Cast;
import walkingkooka.Either;
import walkingkooka.test.Testing;
import walkingkooka.text.CharSequences;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Mixing testing interface for {@link CanConvert}
 */
public interface CanConvertTesting<C extends CanConvert> extends Testing {

    default <T> T convertAndCheck(final Object value,
                                  final Class<T> target,
                                  final T expected) {
        return this.convertAndCheck(this.createCanConvert(), value, target, expected);
    }

    default <T> T convertAndCheck(final CanConvert context,
                                  final Object value,
                                  final Class<T> target,
                                  final T expected) {
        assertEquals(true,
                context.canConvert(value, target),
                () -> context + " can convert(" + CharSequences.quoteIfChars(value) + "(" + value.getClass().getName() + ")," + target.getName() + ")");

        final Either<T, String> result = context.convert(value, target);
        if (result.isRight()) {
            throw new AssertionFailedError(result.rightValue());
        }

        final T convertedValue = result.leftValue();
        checkEquals("Failed to convert " + CharSequences.quoteIfChars(value) + " (" + value.getClass().getName() + ")= to " + target.getName(), expected, convertedValue);
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

    default void convertFails(final Object value,
                              final Class<?> type) {
        this.convertFails(this.createCanConvert(), value, type);
    }

    default void convertFails(final CanConvert context,
                              final Object value,
                              final Class<?> type) {
        final Either<?, String> result = context.convert(value, type);
        result.mapLeft(v -> {
            throw new AssertionFailedError("Expected failure converting " + CharSequences.quoteIfChars(value) + " to " + type.getName() + " but got " + CharSequences.quoteIfChars(v));
        });
    }

    /**
     * Factory that creates a {@link CanConvert}.
     */
    C createCanConvert();
}
