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
import walkingkooka.props.Properties;
import walkingkooka.props.PropertiesPath;

public final class ConverterHasPropertiesTest extends ConverterTestCase2<ConverterHasProperties<ConverterContext>> {

    @Test
    public void testConvertStringToPropertiesFails() {
        this.convertFails(
            "",
            Properties.class
        );
    }

    @Test
    public void testConvertNullToProperties() {
        this.convertAndCheck(
            null,
            Properties.class
        );
    }

    @Test
    public void testConvertHasPropertiesToProperties() {
        final Properties properties = Properties.EMPTY.set(
            PropertiesPath.parse("hello"),
            "world"
        );

        this.convertAndCheck(
            properties
        );
    }

    @Override
    public ConverterHasProperties<ConverterContext> createConverter() {
        return ConverterHasProperties.instance();
    }

    @Override
    public ConverterContext createContext() {
        return ConverterContexts.fake();
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(
            this.createConverter(),
            "HasProperties"
        );
    }

    // class............................................................................................................

    @Override
    public Class<ConverterHasProperties<ConverterContext>> type() {
        return Cast.to(ConverterHasProperties.class);
    }
}
