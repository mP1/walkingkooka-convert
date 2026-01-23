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

import walkingkooka.datetime.DateTimeContext;
import walkingkooka.datetime.DateTimeContextDelegator;
import walkingkooka.math.DecimalNumberContext;
import walkingkooka.math.DecimalNumberContextDelegator;
import walkingkooka.text.Indentation;
import walkingkooka.text.LineEnding;

import java.util.Locale;

public interface ConverterContextDelegator extends ConverterContext,
    ConverterLikeDelegator,
    DateTimeContextDelegator,
    DecimalNumberContextDelegator {

    @Override
    default boolean canNumbersHaveGroupSeparator() {
        return this.converterContext()
            .canNumbersHaveGroupSeparator();
    }

    @Override
    default ConverterLike converterLike() {
        return this.converterContext();
    }

    @Override
    default DateTimeContext dateTimeContext() {
        return this.converterContext();
    }

    @Override
    default DecimalNumberContext decimalNumberContext() {
        return this.converterContext();
    }

    @Override
    default Locale locale() {
        return this.converterContext().locale();
    }

    @Override
    default long dateOffset() {
        return this.converterContext()
            .dateOffset();
    }

    @Override
    default char valueSeparator() {
        return this.converterContext()
            .valueSeparator();
    }

    @Override
    default Indentation indentation() {
        return this.converterContext()
            .indentation();
    }

    @Override
    default LineEnding lineEnding() {
        return this.converterContext()
            .lineEnding();
    }

    ConverterContext converterContext();
}
