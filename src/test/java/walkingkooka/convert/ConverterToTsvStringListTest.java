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
import walkingkooka.collect.list.TsvStringList;

public final class ConverterToTsvStringListTest extends ConverterTestCase2<ConverterToTsvStringList<ConverterContext>> {

    @Test
    public void testConvertNullToTsvStringListFails() {
        this.convertFails(
            null,
            TsvStringList.class
        );
    }

    @Test
    public void testConvertCsvStringListToTsvStringList() {
        final TsvStringList tsvStringList = TsvStringList.parse("a\tb\t1");

        this.convertAndCheck(
            tsvStringList.csvStringList(),
            TsvStringList.class,
            tsvStringList
        );
    }

    @Test
    public void testConvertTsvStringListToTsvStringList() {
        final TsvStringList tsvStringList = TsvStringList.parse("a\tb\t1");

        this.convertAndCheck(
            tsvStringList.tsvStringList(),
            TsvStringList.class,
            tsvStringList
        );
    }

    @Override
    public ConverterToTsvStringList<ConverterContext> createConverter() {
        return ConverterToTsvStringList.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "to TsvStringList"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterToTsvStringList<ConverterContext>> type() {
        return Cast.to(ConverterToTsvStringList.class);
    }
}
