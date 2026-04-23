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
import walkingkooka.currency.CurrencyCode;
import walkingkooka.currency.CurrencyValue;

/**
 * A converter that supports converting text into a {@link CurrencyValue} supporting text such as
 * <pre>
 * "123"
 * CurrencyValue.with( 123, ConverterContext.currencyCode())
 *
 * "123 AUD"
 * CurrencyValue.with( 123, CurrencyCode.parse("AUD"))
 *
 * "AUD 123"
 * CurrencyValue.with( 123, CurrencyCode.parse("AUD"))
 * </pre>
 */
final class ConverterTextToCurrencyValue<C extends ConverterContext> extends ConverterTextTo<CurrencyValue, C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTextToCurrencyValue<C> instance() {
        return Cast.to(INSTANCE);
    }

    private final static ConverterTextToCurrencyValue INSTANCE = new ConverterTextToCurrencyValue<>();

    private ConverterTextToCurrencyValue() {
        super();
    }

    @Override
    Class<CurrencyValue> targetType() {
        return CurrencyValue.class;
    }

    @Override
    public CurrencyValue parseText(final String text,
                                   final Class<?> type,
                                   final C context) {
        CurrencyValue currencyValue = null;

        final int whitespace = text.indexOf(' ');
        if (-1 == whitespace) {
            // missing currencyCode use context
            currencyValue = CurrencyValue.with(
                context.convertOrFail(
                    text,
                    Number.class
                ),
                context.currencyCode()
            );
        } else {
            String firstToken = text.substring(0, whitespace)
                .trim();
            String secondToken = text.substring(whitespace + 1)
                .trim();

            if (3 == firstToken.length()) {
                // try AUD 123
                final CurrencyCode currencyCodeOrNull = context.convert(
                    firstToken,
                    CurrencyCode.class
                ).orElseLeft(null);

                if (null != currencyCodeOrNull) {
                    final Number numberOrNull = context.convert(
                        secondToken,
                        Number.class
                    ).orElseLeft(null);

                    if(null != numberOrNull) {
                        currencyValue = CurrencyValue.with(
                            numberOrNull,
                            currencyCodeOrNull
                        );
                    }
                }
            }

            if (null == currencyValue) {
                // try 123 AUD
                currencyValue = CurrencyValue.with(
                    context.convertOrFail(
                        firstToken,
                        Number.class
                    ),
                    context.convertOrFail(
                        secondToken,
                        CurrencyCode.class
                    )
                );
            }
        }

        return currencyValue;
    }
}
