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

import java.time.LocalDateTime;
import java.time.LocalTime;

public final class ConverterLocalTimeLocalDateTimeTest extends ConverterLocalTimeTestCase<ConverterLocalTimeLocalDateTime> {

    @Test
    public void testLocalDateTimeFails() {
        this.convertFails(LocalDateTime.now(), LocalDateTime.class);
    }

    @Test
    public void testLocalTime() {
        this.convertAndCheck(LocalTime.of(12, 58, 59, 789),
                LocalDateTime.class,
                LocalDateTime.of(1970, 1, 1, 12, 58, 59, 789));
    }

    @Override
    public ConverterLocalTimeLocalDateTime createConverter() {
        return ConverterLocalTimeLocalDateTime.INSTANCE;
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<ConverterLocalTimeLocalDateTime> type() {
        return ConverterLocalTimeLocalDateTime.class;
    }

    // TypeNameTesting..................................................................................................

    @Override
    public String typeNameSuffix() {
        return LocalDateTime.class.getSimpleName();
    }
}
