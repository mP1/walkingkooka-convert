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
import walkingkooka.HashCodeEqualsDefinedTesting2;
import walkingkooka.datetime.DateTimeContexts;
import walkingkooka.math.DecimalNumberContexts;
import walkingkooka.text.cursor.parser.BigDecimalParserToken;
import walkingkooka.text.cursor.parser.InvalidCharacterExceptionFactory;
import walkingkooka.text.cursor.parser.Parser;
import walkingkooka.text.cursor.parser.ParserContext;
import walkingkooka.text.cursor.parser.ParserContexts;
import walkingkooka.text.cursor.parser.ParserToken;
import walkingkooka.text.cursor.parser.Parsers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class ConverterParserTest extends ConverterTestCase2<ConverterParser<BigDecimal, ParserContext, ConverterContext>>
    implements HashCodeEqualsDefinedTesting2<ConverterParser<BigDecimal, ParserContext, ConverterContext>> {

    private final static Class<BigDecimal> VALUE_TYPE = BigDecimal.class;

    private final static Parser<ParserContext> PARSER = Parsers.bigDecimal();

    private final static Function<ConverterContext, ParserContext> CONVERTER_CONTEXT_PARSER_CONTEXT_FUNCTION = (c) -> ParserContexts.basic(
        false, // canNumbersHaveGroupSeparator
        InvalidCharacterExceptionFactory.POSITION,
        ',', // valueSeparator
        c,
        c
    );

    private final static BiFunction<ParserToken, ConverterContext, BigDecimal> PARSER_TOKEN_TO_VALUE = (t, c) -> t.cast(BigDecimalParserToken.class).value();

    @Test
    public void testWithNullParserValueTypeFails() {
        assertThrows(
            NullPointerException.class,
            () -> ConverterParser.with(
                null,
                PARSER,
                CONVERTER_CONTEXT_PARSER_CONTEXT_FUNCTION,
                PARSER_TOKEN_TO_VALUE
            )
        );
    }

    @Test
    public void testWithNullParserFails() {
        assertThrows(
            NullPointerException.class,
            () -> ConverterParser.with(
                VALUE_TYPE,
                null,
                CONVERTER_CONTEXT_PARSER_CONTEXT_FUNCTION,
                PARSER_TOKEN_TO_VALUE
            )
        );
    }

    @Test
    public void testWithNullConverterContextToParserContextFails() {
        assertThrows(
            NullPointerException.class,
            () -> ConverterParser.with(
                VALUE_TYPE,
                PARSER,
                null,
                PARSER_TOKEN_TO_VALUE
            )
        );
    }

    @Test
    public void testWithNullParserTokenToValueFails() {
        assertThrows(
            NullPointerException.class,
            () -> ConverterParser.with(
                VALUE_TYPE,
                PARSER,
                CONVERTER_CONTEXT_PARSER_CONTEXT_FUNCTION,
                null
            )
        );
    }

    @Test
    public void testParserBigDecimalConvertNullToBigDecimal() {
        this.convertAndCheck(
            null,
            VALUE_TYPE
        );
    }

    @Test
    public void testParserBigDecimalConvertCharSequenceToBigDecimal() {
        this.convertAndCheck(
            new StringBuffer("1.23"),
            VALUE_TYPE,
            BigDecimal.valueOf(1.23)
        );
    }

    @Test
    public void testParserBigDecimalConvertStringToBigDecimal() {
        this.convertAndCheck(
            "1.23",
            VALUE_TYPE,
            BigDecimal.valueOf(1.23)
        );
    }

    @Test
    public void testParserBigDecimalConvertIntegerToBigDecimalFails() {
        this.convertFails(
            Integer.MAX_VALUE,
            VALUE_TYPE
        );
    }

    @Test
    public void testParserBigDecimalConvertStringToBigDecimalFails() {
        this.convertFails(
            "FAILS",
            VALUE_TYPE
        );
    }

    @Override
    public ConverterParser<BigDecimal, ParserContext, ConverterContext> createConverter() {
        return ConverterParser.with(
            VALUE_TYPE,
            PARSER,
            CONVERTER_CONTEXT_PARSER_CONTEXT_FUNCTION,
            PARSER_TOKEN_TO_VALUE
        );
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.basic(
            false, // canNumbersHaveGroupSeparator
            0, // dateOffset
            ',', // valueSeparator
            Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString(),
            DateTimeContexts.fake(),
            DecimalNumberContexts.american(MathContext.DECIMAL32)
        );
    }

    // hashCode/equals..................................................................................................

    @Test
    public void testEqualsDifferentValueTypeClass() {
        this.checkNotEquals(
            ConverterParser.with(
                Void.class,
                PARSER,
                CONVERTER_CONTEXT_PARSER_CONTEXT_FUNCTION,
                Cast.to(PARSER_TOKEN_TO_VALUE)
            )
        );
    }

    @Test
    public void testEqualsDifferentParser() {
        this.checkNotEquals(
            ConverterParser.with(
                VALUE_TYPE,
                Parsers.fake(),
                CONVERTER_CONTEXT_PARSER_CONTEXT_FUNCTION,
                PARSER_TOKEN_TO_VALUE
            )
        );
    }

    @Test
    public void testEqualsDifferentConverterContextParserContextFunction() {
        this.checkNotEquals(
            ConverterParser.with(
                VALUE_TYPE,
                PARSER,
                (c) -> {
                    throw new UnsupportedOperationException();
                },
                PARSER_TOKEN_TO_VALUE
            )
        );
    }

    @Test
    public void testEqualsDifferentParserTokenToValue() {
        this.checkNotEquals(
            ConverterParser.with(
                VALUE_TYPE,
                PARSER,
                CONVERTER_CONTEXT_PARSER_CONTEXT_FUNCTION,
                (t, c) -> {
                    throw new UnsupportedOperationException();
                }
            )
        );
    }

    @Override
    public ConverterParser<BigDecimal, ParserContext, ConverterContext> createObject() {
        return this.createConverter();
    }

    // toString.........................................................................................................

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "String to BigDecimal"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterParser<BigDecimal, ParserContext, ConverterContext>> type() {
        return Cast.to(ConverterParser.class);
    }


    @Override
    public String typeNamePrefix() {
        return Converter.class.getSimpleName();
    }
}
