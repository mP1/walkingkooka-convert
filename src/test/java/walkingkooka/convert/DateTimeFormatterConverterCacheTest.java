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
import walkingkooka.ToStringTesting;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class DateTimeFormatterConverterCacheTest extends DateTimeFormatterConverterTestCase<DateTimeFormatterConverterCache>
        implements ToStringTesting<DateTimeFormatterConverterCache> {

    @Test
    public void testToString() {
        final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        this.toStringAndCheck(DateTimeFormatterConverterCache.with(Locale.ENGLISH, 123, formatter),
                "en 123 " + formatter);
    }

    @Override
    public Class<DateTimeFormatterConverterCache> type() {
        return DateTimeFormatterConverterCache.class;
    }

    @Override
    public String typeNameSuffix() {
        return "Cache";
    }
}
