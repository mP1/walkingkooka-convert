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
import walkingkooka.math.NumberTypeVisitorTesting;

public final class BooleanToNumberConverterNumberTypeVisitorTest extends ConverterTestCase<BooleanToNumberConverterNumberTypeVisitor>
    implements NumberTypeVisitorTesting<BooleanToNumberConverterNumberTypeVisitor> {

    @Override
    public void testAllConstructorsVisibility() {
    }

    @Override
    public void testIfClassIsFinalIfAllConstructorsArePrivate() {
    }

    @Test
    public void testToString() {
        this.toStringAndCheck(this.createVisitor(), "Boolean->Number true null");
    }

    @Test
    public void testToString2() {
        final BooleanToNumberConverterNumberTypeVisitor visitor = new BooleanToNumberConverterNumberTypeVisitor(true);
        visitor.number = 123;
        this.toStringAndCheck(visitor, "Boolean->Number true 123");
    }

    @Test
    public void testToStringFalseZero() {
        final BooleanToNumberConverterNumberTypeVisitor visitor = new BooleanToNumberConverterNumberTypeVisitor(false);
        visitor.number = 0;
        this.toStringAndCheck(visitor, "Boolean->Number false 0");
    }

    @Override
    public BooleanToNumberConverterNumberTypeVisitor createVisitor() {
        return new BooleanToNumberConverterNumberTypeVisitor(true);
    }

    // ClassTesting.....................................................................................................

    @Override
    public Class<BooleanToNumberConverterNumberTypeVisitor> type() {
        return BooleanToNumberConverterNumberTypeVisitor.class;
    }

    // TypeNameTesting..................................................................................................

    @Override
    public String typeNamePrefix() {
        return BooleanToNumberConverter.class.getSimpleName();
    }
}
