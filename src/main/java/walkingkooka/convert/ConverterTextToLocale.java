package walkingkooka.convert;

import walkingkooka.Cast;

import java.util.Locale;

/**
 * A {@link Converter} that converts an Locale as a {@link String} into a {@link Locale}.
 */
final class ConverterTextToLocale<C extends ConverterContext> implements TextToTryingShortCircuitingConverter<C> {

    /**
     * Type safe instance getter
     */
    static <C extends ConverterContext> ConverterTextToLocale<C> instance() {
        return Cast.to(INSTANCE);
    }

    private final static ConverterTextToLocale<?> INSTANCE = new ConverterTextToLocale<>();

    private ConverterTextToLocale() {
        super();
    }

    @Override
    public boolean isTargetType(final Object value,
                                final Class<?> type,
                                final C context) {
        return value != Locale.class &&
            Locale.class == type;
    }

    @Override
    public Object parseText(final String value,
                            final Class<?> type,
                            final C context) {
        return Locale.forLanguageTag(value);
    }

    @Override
    public String toString() {
        return "Text to " + Locale.class.getSimpleName();
    }
}
