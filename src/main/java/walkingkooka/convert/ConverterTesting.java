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
import walkingkooka.text.CharSequences;
import walkingkooka.text.printer.TreePrintableTesting;

import java.util.function.Supplier;

/**
 * A mixin interface with helpers to assist testing {@link Converter} converts or the conversion fails.
 */
public interface ConverterTesting extends TreePrintableTesting {

    default <C extends ConverterContext> void canConvertAndCheck(final Converter<C> converter,
                                                                 final Object value,
                                                                 final Class<?> target,
                                                                 final C context,
                                                                 final boolean expected) {
        final Supplier<String> className = () -> null != value ?
            " (" + value.getClass().getName() + ")" :
            "";
        this.checkEquals(
            expected,
            converter.canConvert(
                value,
                target,
                context
            ),
            () -> converter + " can convert " + CharSequences.quoteIfChars(value) + className.get() + " to " + target.getName()
        );
    }

    default <T, C extends ConverterContext> T convertAndCheck(final Converter<C> converter,
                                                              final Object value,
                                                              final Class<T> target,
                                                              final C context,
                                                              final T expected) {
        this.canConvertAndCheck(
            converter,
            value,
            target,
            context,
            true
        );

        final Supplier<String> className = () -> null != value ?
            " (" + value.getClass().getName() + ")" :
            "";

        final Either<T, String> result = converter.convert(
            value,
            target,
            context
        );
        this.checkEquals(
            false,
            result.isRight(),
            () -> converter + " convert " + CharSequences.quoteIfChars(value) + className.get() + " to " + target.getName()
        );

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
                TreePrintableTesting.super.checkEquals(expected, actual, message);
            }
        } else {
            TreePrintableTesting.super.checkEquals(expected, actual, message);
        }
    }

    // convertFails.....................................................................................................

    default <C extends ConverterContext> void convertFails(final Converter<C> converter,
                                                           final Object value,
                                                           final Class<?> type,
                                                           final C context) {
        this.convertFails(
            converter,
            value,
            type,
            context,
            (String) null // no expected message.
        );
    }

    default <C extends ConverterContext> void convertFails(final Converter<C> converter,
                                                           final Object value,
                                                           final Class<?> type,
                                                           final C context,
                                                           final String expected) {
        this.convertFails(
            converter,
            value,
            Cast.to(type),
            context,
            null != expected ?
                () -> expected :
                null
        );
    }

    default <C extends ConverterContext, T> void convertFails(final Converter<C> converter,
                                                              final Object value,
                                                              final Class<T> type,
                                                              final C context,
                                                              final Supplier<String> expected) {
        final Either<?, String> result = converter.convert(value, type, context);
        if (null != expected) {
            this.checkEquals(
                Either.right(
                    expected.get()
                ),
                result
            );
        } else {
            result.mapLeft(
                v -> {
                    throw new AssertionFailedError("Expected failure converting " + CharSequences.quoteIfChars(value) + " to " + type.getName() + " but got " + CharSequences.quoteIfChars(v));
                }
            );
        }
    }
}
