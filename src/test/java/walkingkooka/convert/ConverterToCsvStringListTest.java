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
import walkingkooka.collect.list.CsvStringList;

public final class ConverterToCsvStringListTest extends ConverterTestCase2<ConverterToCsvStringList<ConverterContext>> {

    @Test
    public void testConvertNullToCsvStringListFails() {
        this.convertFails(
            null,
            walkingkooka.collect.list.CsvStringList.class
        );
    }

    @Test
    public void testConvertCsvStringListToCsvStringList() {
        final CsvStringList csvStringList = CsvStringList.parse("a,b,1");

        this.convertAndCheck(
            csvStringList,
            CsvStringList.class,
            csvStringList
        );
    }

    @Test
    public void testConvertTsvStringListToCsvStringList() {
        final CsvStringList csvStringList = CsvStringList.parse("a,b,1");

        this.convertAndCheck(
            csvStringList.tsvStringList(),
            CsvStringList.class,
            csvStringList
        );
    }

    @Override
    public ConverterToCsvStringList<ConverterContext> createConverter() {
        return ConverterToCsvStringList.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "to CsvStringList"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterToCsvStringList<ConverterContext>> type() {
        return Cast.to(ConverterToCsvStringList.class);
    }
}
