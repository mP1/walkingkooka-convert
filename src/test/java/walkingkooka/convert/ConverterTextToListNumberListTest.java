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
import walkingkooka.math.NumberList;

public final class ConverterTextToListNumberListTest extends ConverterTextToListTestCase<NumberList, Number, ConverterTextToListNumberList<ConverterContext>> {

    private final static Number NUMBER = 1;

    private final static String NUMBER_STRING = "1";

    private final static Number NUMBER2 = 22;

    @Test
    public void testConvertString() {
        this.convertToListAndCheck(
            NUMBER_STRING,
            NUMBER
        );
    }

    @Test
    public void testConvertCharSequenceNumber() {
        this.convertToListAndCheck(
            new StringBuilder(NUMBER_STRING),
            NUMBER
        );
    }

    @Test
    public void testConvertStringQuotedNumber() {
        this.convertToListAndCheck(
            "\"1\"",
            NUMBER
        );
    }

    @Test
    public void testConvertNumberSeparatorNumber() {
        this.convertToListAndCheck(
            "1*22",
            NUMBER,
            NUMBER2
        );
    }

    @Test
    public void testConvertNumberSeparatorNumberSeparatorNumber() {
        this.convertToListAndCheck(
            "1*22*1",
            NUMBER,
            NUMBER2,
            NUMBER
        );
    }

    @Test
    public void testConvertNumberSeparatorNumberSeparatorNumberExtraSpacesIgnored() {
        this.convertToListAndCheck(
            "1 * 22 *1 ",
            NUMBER,
            NUMBER2,
            NUMBER
        );
    }

    @Test
    public void testConvertNumberSeparatorNumberSeparatorNumberExtraSpacesIgnored2() {
        this.convertToListAndCheck(
            " 1 * 22 * 1 ",
            NUMBER,
            NUMBER2,
            NUMBER
        );
    }

    @Override
    public ConverterTextToListNumberList<ConverterContext> createConverter() {
        return ConverterTextToListNumberList.instance();
    }

    @Override
    public ConverterContext createContext() {
        return new FakeConverterContext() {

            @Override
            public char valueSeparator() {
                return '*';
            }

            @Override
            public boolean canConvert(final Object value,
                                      final Class<?> type) {
                return this.converter.canConvert(
                    value,
                    type,
                    this
                );
            }

            @Override
            public <T> Either<T, String> convert(final Object value,
                                                 final Class<T> target) {
                return this.converter.convert(
                    value,
                    target,
                    this
                );
            }

            private final Converter<FakeConverterContext> converter = Converters.collection(
                Lists.of(
                    Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString(),
                    new ShortCircuitingConverter<>() {
                        @Override
                        public boolean canConvert(final Object value,
                                                  final Class<?> type,
                                                  final FakeConverterContext context) {
                            return value instanceof String && Number.class == type;
                        }

                        @Override
                        public <T> Either<T, String> doConvert(final Object value,
                                                               final Class<T> type,
                                                               final FakeConverterContext context) {
                            return this.successfulConversion(
                                Integer.parseInt(
                                    (String) value
                                ),
                                type
                            );
                        }
                    }
                )
            );
        };
    }

    @Override
    Class<NumberList> listType() {
        return NumberList.class;
    }

    @Override
    NumberList emptyList() {
        return NumberList.EMPTY;
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToListNumberList<ConverterContext>> type() {
        return Cast.to(ConverterTextToListNumberList.class);
    }
}
