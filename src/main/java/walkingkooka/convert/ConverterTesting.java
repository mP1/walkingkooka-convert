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

import java.util.function.Supplier;

/**
 * A mixin interface with helpers to assist testing {@link Converter} converts or the conversion fails.
 */
public interface ConverterTesting extends Testing {

    default <T, C extends ConverterContext> T convertAndCheck(final Converter<C> converter,
                                                              final Object value,
                                                              final Class<T> target,
                                                              final C context,
                                                              final T expected) {
        final Supplier<String> className = () -> null != value ?
                " (" + value.getClass().getName() + ")" :
                "";
        this.checkEquals(
                true,
                converter.canConvert(value, target, context),
                () -> converter + " can convert " + CharSequences.quoteIfChars(value) + className.get() + " to " + target.getName() + ")"
        );

        final Either<T, String> result = converter.convert(value, target, context);
        if (result.isRight()) {
            throw new AssertionFailedError(result.rightValue());
        }

        final T convertedValue = result.leftValue();
        this.checkEquals(
                expected,
                convertedValue,
                () -> "Failed to convert " + CharSequences.quoteIfChars(value) + className.get() + "= to " + target.getName()
        );
        return convertedValue;
    }

    @Override
    default void checkEquals(final Object expected,
                             final Object actual,
                             final Supplier<String> message) {
        if (expected instanceof Comparable && expected.getClass().isInstance(actual)) {
            final Comparable<?> expectedComparable = Cast.to(expected);
            if (expectedComparable.compareTo(Cast.to(actual)) != 0) {
                Testing.super.checkEquals(expected, actual, message);
            }
        } else {
            Testing.super.checkEquals(expected, actual, message);
        }
    }

    default <C extends ConverterContext> void convertFails(final Converter<C> converter,
                                                           final Object value,
                                                           final Class<?> type,
                                                           final C context) {
        final Either<?, String> result = converter.convert(value, type, context);
        result.mapLeft(v -> {
            throw new AssertionFailedError("Expected failure converting " + CharSequences.quoteIfChars(value) + " to " + type.getName() + " but got " + CharSequences.quoteIfChars(v));
        });
    }
}
