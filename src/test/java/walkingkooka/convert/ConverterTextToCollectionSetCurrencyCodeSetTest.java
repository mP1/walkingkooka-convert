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
import walkingkooka.currency.CurrencyCode;
import walkingkooka.currency.CurrencyCodeSet;

public final class ConverterTextToCollectionSetCurrencyCodeSetTest extends ConverterTextToCollectionSetTestCase<CurrencyCodeSet, CurrencyCode, ConverterTextToCollectionSetCurrencyCodeSet<ConverterContext>> {

    private final static CurrencyCode CURRENCY_CODE = CurrencyCode.parse("AUD");

    private final static CurrencyCode CURRENCY_CODE2 = CurrencyCode.parse("NZD");

    private final static char SEPARATOR = ',';

    @Test
    public void testConvertString() {
        this.convertToCollectionAndCheck(
            CURRENCY_CODE.toString(),
            CURRENCY_CODE
        );
    }

    @Test
    public void testConvertStringSurroundingSpacesTrimmed() {
        this.convertToCollectionAndCheck(
            " " + CURRENCY_CODE2 + " ",
            CURRENCY_CODE2
        );
    }

    @Test
    public void testConvertCharSequenceString() {
        this.convertToCollectionAndCheck(
            new StringBuilder(CURRENCY_CODE.toString()),
            CURRENCY_CODE
        );
    }

    @Test
    public void testConvertStringQuotedString() {
        this.convertToCollectionAndCheck(
            "\"" + CURRENCY_CODE + "\"",
            CURRENCY_CODE
        );
    }

    @Test
    public void testConvertStringSeparatorString() {
        this.convertToCollectionAndCheck(
            "" + CURRENCY_CODE + SEPARATOR + CURRENCY_CODE2,
            CURRENCY_CODE,
            CURRENCY_CODE2
        );
    }

    @Test
    public void testConvertStringSeparatorStringSeparatorString() {
        this.convertToCollectionAndCheck(
            "" + CURRENCY_CODE + SEPARATOR + CURRENCY_CODE2 + SEPARATOR + CURRENCY_CODE,
            CURRENCY_CODE,
            CURRENCY_CODE2,
            CURRENCY_CODE
        );
    }

    @Override
    public ConverterTextToCollectionSetCurrencyCodeSet<ConverterContext> createConverter() {
        return ConverterTextToCollectionSetCurrencyCodeSet.instance();
    }

    @Override
    public ConverterContext createContext() {
        return new FakeConverterContext() {

            @Override
            public char valueSeparator() {
                return SEPARATOR;
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
                    Converters.textToCurrencyCode()
                )
            );
        };
    }

    @Override
    Class<CurrencyCodeSet> collectionType() {
        return CurrencyCodeSet.class;
    }

    @Override
    CurrencyCodeSet emptySet() {
        return CurrencyCodeSet.EMPTY;
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToCollectionSetCurrencyCodeSet<ConverterContext>> type() {
        return Cast.to(ConverterTextToCollectionSetCurrencyCodeSet.class);
    }
}
