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
import walkingkooka.collect.list.BooleanList;
import walkingkooka.collect.list.Lists;

public final class ConverterTextToCollectionListBooleanListTest extends ConverterTextToCollectionListTestCase<BooleanList, Boolean, ConverterTextToCollectionListBooleanList<ConverterContext>> {

    @Test
    public void testConvertStringTrue() {
        this.convertToCollectionAndCheck(
            "TRUE",
            Boolean.TRUE
        );
    }

    @Test
    public void testConvertCharSequenceTrue() {
        this.convertToCollectionAndCheck(
            new StringBuilder("TRUE"),
            Boolean.TRUE
        );
    }

    @Test
    public void testConvertStringQuotedTrue() {
        this.convertToCollectionAndCheck(
            "\"TRUE\"",
            Boolean.TRUE
        );
    }

    @Test
    public void testConvertStringQuotedBackslashTrue() {
        this.convertToCollectionAndCheck(
            "\"\\TRUE\"",
            Boolean.TRUE
        );
    }

    @Test
    public void testConvertStringSpacesQuotedTrue() {
        this.convertToCollectionAndCheck(
            " \"TRUE\"",
            Boolean.TRUE
        );
    }

    @Test
    public void testConvertStringSpaceSpaceQuotedTrue() {
        this.convertToCollectionAndCheck(
            "  \"TRUE\"",
            Boolean.TRUE
        );
    }

    @Test
    public void testConvertStringQuotedTrueSpaces() {
        this.convertToCollectionAndCheck(
            "\"TRUE\" ",
            Boolean.TRUE
        );
    }

    @Test
    public void testConvertStringQuotedTrueSpaceSpace() {
        this.convertToCollectionAndCheck(
            "\"TRUE\" ",
            Boolean.TRUE
        );
    }

    @Test
    public void testConvertStringFalse() {
        this.convertToCollectionAndCheck(
            "FALSE",
            Boolean.FALSE
        );
    }

    @Test
    public void testConvertStringSpaceTrue() {
        this.convertToCollectionAndCheck(
            " TRUE",
            Boolean.TRUE
        );
    }

    @Test
    public void testConvertStringTrueSpace() {
        this.convertToCollectionAndCheck(
            " TRUE",
            Boolean.TRUE
        );
    }

    @Test
    public void testConvertTrueSeparatorFails() {
        this.convertToCollectionFails(
            "TRUE*"
        );
    }

    @Test
    public void testConvertTrueSeparatorFalse() {
        this.convertToCollectionAndCheck(
            "TRUE*FALSE",
            true,
            false
        );
    }

    @Test
    public void testConvertTrueSeparatorFalseSeparatorTrue() {
        this.convertToCollectionAndCheck(
            "TRUE*FALSE*TRUE",
            true,
            false,
            true
        );
    }

    @Test
    public void testConvertTrueSeparatorFalseSeparatorTrueExtraSpacesIgnored() {
        this.convertToCollectionAndCheck(
            " TRUE * FALSE * TRUE ",
            true,
            false,
            true
        );
    }

    @Test
    public void testConvertTrueSeparatorFalseSeparatorTrueExtraSpacesIgnored2() {
        this.convertToCollectionAndCheck(
            " true * false * true ",
            true,
            false,
            true
        );
    }

    @Override
    public ConverterTextToCollectionListBooleanList<ConverterContext> createConverter() {
        return ConverterTextToCollectionListBooleanList.instance();
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
                            return value instanceof String &&
                                (
                                    Boolean.FALSE.toString().equalsIgnoreCase(value.toString()) ||
                                        Boolean.TRUE.toString().equalsIgnoreCase(value.toString())
                                ) && Boolean.class == type;
                        }

                        @Override
                        public <T> Either<T, String> doConvert(final Object value,
                                                               final Class<T> type,
                                                               final FakeConverterContext context) {
                            return this.successfulConversion(
                                Boolean.TRUE.toString()
                                    .equalsIgnoreCase(value.toString()),
                                type
                            );
                        }
                    }
                )
            );
        };
    }

    @Override
    Class<BooleanList> collectionType() {
        return BooleanList.class;
    }

    @Override
    BooleanList emptyList() {
        return BooleanList.EMPTY;
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToCollectionListBooleanList<ConverterContext>> type() {
        return Cast.to(ConverterTextToCollectionListBooleanList.class);
    }
}
