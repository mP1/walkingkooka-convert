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

public final class ParserConverterTest extends ConverterTestCase2<ParserConverter<BigDecimal, ParserContext, ConverterContext>> {

    @Test
    public void testWithNullParserValueTypeFails() {
        assertThrows(
                NullPointerException.class,
                () -> ParserConverter.with(
                        null,
                        this.parser(),
                        this.converterContextToParserContext(),
                        this.parserTokenToValue()
                )
        );
    }

    @Test
    public void testWithNullParserFails() {
        assertThrows(
                NullPointerException.class,
                () -> ParserConverter.with(
                        BigDecimal.class,
                        null,
                        this.converterContextToParserContext(),
                        this.parserTokenToValue()
                )
        );
    }

    @Test
    public void testWithNullConverterContextToParserContextFails() {
        assertThrows(
                NullPointerException.class,
                () -> ParserConverter.with(
                        BigDecimal.class,
                        this.parser(),
                        null,
                        this.parserTokenToValue()
                )
        );
    }

    @Test
    public void testWithNullParserTokenToValueFails() {
        assertThrows(
                NullPointerException.class,
                () -> ParserConverter.with(
                        BigDecimal.class,
                        this.parser(),
                        this.converterContextToParserContext(),
                        null
                )
        );
    }

    @Test
    public void testConvertNull() {
        this.convertAndCheck(
                null,
                BigDecimal.class
        );
    }

    @Test
    public void testConvertParsed() {
        this.convertAndCheck(
                "1.23",
                BigDecimal.class,
                BigDecimal.valueOf(1.23)
        );
    }

    @Test
    public void testConvertParserNonStringFails() {
        this.convertFails(
                Integer.MAX_VALUE,
                BigDecimal.class
        );
    }

    @Test
    public void testConvertParserFails() {
        this.convertFails(
                "FAILS",
                BigDecimal.class
        );
    }

    @Override
    public ParserConverter<BigDecimal, ParserContext, ConverterContext> createConverter() {
        return ParserConverter.with(
                BigDecimal.class,
                this.parser(),
                this.converterContextToParserContext(),
                this.parserTokenToValue()
        );
    }

    private Function<ConverterContext, ParserContext> converterContextToParserContext() {
        return (c) -> ParserContexts.basic(
                InvalidCharacterExceptionFactory.POSITION,
                c,
                c
        );
    }

    private BiFunction<ParserToken, ConverterContext, BigDecimal> parserTokenToValue() {
        return (t, c) -> t.cast(BigDecimalParserToken.class).value();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.basic(
                0, // dateOffset
                Converters.fake(),
                DateTimeContexts.fake(),
                DecimalNumberContexts.american(MathContext.DECIMAL32)
        );
    }

    private Parser<ParserContext> parser() {
        return Parsers.bigDecimal();
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
    public Class<ParserConverter<BigDecimal, ParserContext, ConverterContext>> type() {
        return Cast.to(ParserConverter.class);
    }
}
