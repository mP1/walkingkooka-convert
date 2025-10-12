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

import walkingkooka.SystemException;
import walkingkooka.Value;

import java.util.Objects;

/**
 * An {@link RuntimeException} that reports that a value could not be converted to type.
 * <br>
 * This is typically thrown by {@link Converter#convertOrFail(Object, Class, ConverterContext)} to report conversion failures.
 */
public class ConverterException extends SystemException implements Value<Object> {

    private static final long serialVersionUID = 1;

    protected ConverterException() {
        super();
    }

    public ConverterException(final String message,
                              final Object value,
                              final Class<?> type) {
        super(message);
        this.value = value;
        this.type = Objects.requireNonNull(type, "type");
    }

    public ConverterException(final String message,
                              final Object value,
                              final Class<?> type,
                              final Throwable cause) {
        super(message, cause);
        this.value = value;
        this.type = Objects.requireNonNull(type, "type");
    }

    // Value............................................................................................................

    @Override
    public Object value() {
        return this.value;
    }

    private Object value;

    // type.............................................................................................................

    public Class<?> type() {
        return this.type;
    }

    private Class<?> type;
}
