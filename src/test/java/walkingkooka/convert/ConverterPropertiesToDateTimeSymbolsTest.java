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
import walkingkooka.datetime.DateTimeSymbols;
import walkingkooka.props.Properties;

public final class ConverterPropertiesToDateTimeSymbolsTest extends ConverterPropertiesToTestCase<ConverterPropertiesToDateTimeSymbols<ConverterContext>, DateTimeSymbols> {

    private final static Properties PROPERTIES = Properties.parse(
        "ampms=am,pm\r\n" +
            "monthNameAbbreviations=Jan,Feb,Mar,Apr,May,Jun,Jul,Aug,Sep,Oct,Nov,Dec\r\n" +
            "monthNames=January,February,March,April,May,June,July,August,September,October,November,December\r\n" +
            "weekDayNameAbbreviations=Sun,Mon,Tu,Wed,Thu,Fri,Sat\r\n" +
            "weekDayNames=Sunday,Monday,Tuesday,Wednesday,Thursday,Friday,Saturday"
    );

    @Test
    public void testConvertPropertiesToDateTimeSymbols() {
        this.convertAndCheck(
            PROPERTIES,
            DateTimeSymbols.fromProperties(PROPERTIES)
        );
    }

    @Test
    public void testConvertStringToDateTimeSymbols() {
        this.convertAndCheck(
            PROPERTIES.toString(),
            DateTimeSymbols.fromProperties(PROPERTIES)
        );
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "Properties to DateTimeSymbols"
        );
    }

    @Override
    public ConverterPropertiesToDateTimeSymbols<ConverterContext> createConverter() {
        return ConverterPropertiesToDateTimeSymbols.instance();
    }

    @Override
    public Class<ConverterPropertiesToDateTimeSymbols<ConverterContext>> type() {
        return Cast.to(ConverterPropertiesToDateTimeSymbols.class);
    }
}
