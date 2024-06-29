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

import walkingkooka.Either;
import walkingkooka.text.cursor.TextCursor;
import walkingkooka.text.cursor.TextCursors;
import walkingkooka.text.cursor.parser.Parser;
import walkingkooka.text.cursor.parser.ParserContext;
import walkingkooka.text.cursor.parser.ParserToken;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * A {@link Converter} that accepts only {@link String} and then invokes a {@link Parser}. If the parser is successful,
 * the {@link Function parserTokenToValue} is used to make the {@link ParserToken} into the target value.
 */
final class ParserConverter<V, P extends ParserContext, C extends ConverterContext> implements Converter<C> {

    static <V, P extends ParserContext, C extends ConverterContext> ParserConverter<V, P, C> with(final Class<V> parserValueType,
                                                                                                  final Parser<P> parser,
                                                                                                  final Function<C, P> converterContextToParserContext,
                                                                                                  final BiFunction<ParserToken, C, V> parserTokenToValue) {
        Objects.requireNonNull(parserValueType, "parserValueType");
        Objects.requireNonNull(parser, "parser");
        Objects.requireNonNull(converterContextToParserContext, "converterContextToParserContext");
        Objects.requireNonNull(parserTokenToValue, "parserTokenToValue");

        return new ParserConverter<>(
                parserValueType,
                parser,
                converterContextToParserContext,
                parserTokenToValue
        );
    }

    /**
     * Private ctor use factory.
     */
    private ParserConverter(final Class<V> parserValueType,
                            final Parser<P> parser,
                            final Function<C, P> converterContextToParserContext,
                            final BiFunction<ParserToken, C, V> parserTokenToValue) {
        this.parserValueType = parserValueType;
        this.parser = parser;
        this.converterContextToParserContext = converterContextToParserContext;
        this.parserTokenToValue = parserTokenToValue;
    }

    @Override
    public boolean canConvert(final Object value,
                              final Class<?> type,
                              final C context) {
        return (null == value || value instanceof String) && this.parserValueType == type;
    }

    private final Class<V> parserValueType;

    @Override
    public <T> Either<T, String> convert(final Object value,
                                         final Class<T> type,
                                         final C context) {
        return this.canConvert(value, type, context) ?
                this.parseString((String) value, type, context) :
                this.failConversion(value, type);
    }

    private <T> Either<T, String> parseString(final String text,
                                              final Class<T> type,
                                              final C context) {
        return null == text ?
                this.successfulConversion(null, type) :
                this.parseNonNullString(text, type, context);
    }


    private <T> Either<T, String> parseNonNullString(final String text,
                                                     final Class<T> type,
                                                     final C context) {
        final TextCursor cursor = TextCursors.charSequence(text);
        final Optional<ParserToken> result = this.parser.parse(
                cursor,
                this.converterContextToParserContext.apply(context)
        );
        return result.isPresent() && cursor.isEmpty() ?
                this.successfulConversion(
                        this.parserTokenToValue.apply(
                                result.get(),
                                context
                        ),
                        type
                ) :
                this.failConversion(
                        text,
                        type
                );
    }

    private final Parser<P> parser;

    /**
     * This {@link Function} adapts the {@link ConverterContext} into a {@link ParserContext} for the {@link Parser} to
     * use when it parses the given {@link String text}.
     */
    private final Function<C, P> converterContextToParserContext;

    /**
     * A {@link Function} that is invoked with the {@link ParserToken} to give the converted result value.
     */
    private final BiFunction<ParserToken, C, V> parserTokenToValue;

    @Override
    public String toString() {
        return "String to " + this.parserValueType.getSimpleName();
    }
}
