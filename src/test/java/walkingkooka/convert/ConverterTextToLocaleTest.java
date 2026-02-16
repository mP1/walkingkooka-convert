package walkingkooka.convert;

import org.junit.jupiter.api.Test;
import walkingkooka.Cast;
import walkingkooka.Either;

import java.util.Locale;

public final class ConverterTextToLocaleTest extends ConverterTestCase2<ConverterTextToLocale<ConverterContext>> {

    @Test
    public void testConvertStringToStringFails() {
        this.convertFails(
            "EN-AU",
            String.class
        );
    }

    @Test
    public void testConvertLocaleToLocaleFails() {
        this.convertFails(
            Locale.forLanguageTag("EN-AU"),
            Locale.class
        );
    }

    @Test
    public void testConvertStringToLocale() {
        final Locale locale = Locale.forLanguageTag("EN-AU");

        this.convertAndCheck(
            locale.toLanguageTag(),
            locale
        );
    }

    @Test
    public void testConvertCharSequenceToLocale() {
        final Locale locale = Locale.forLanguageTag("EN-AU");

        this.convertAndCheck(
            new StringBuilder(locale.toLanguageTag()),
            locale
        );
    }

    @Override
    public ConverterTextToLocale<ConverterContext> createConverter() {
        return ConverterTextToLocale.instance();
    }

    @Override
    public FakeConverterContext createContext() {
        return new FakeConverterContext() {
            @Override
            public boolean canConvert(final Object value,
                                      final Class<?> type) {
                return converter.canConvert(
                    value,
                    type,
                    this
                );
            }

            @Override
            public <T> Either<T, String> convert(final Object value,
                                                 final Class<T> target) {
                return this.converter.convert(
                    value,
                    target,
                    this
                );
            }

            private final Converter<ConverterContext> converter = Converters.characterOrCharSequenceOrHasTextOrStringToCharacterOrCharSequenceOrString();
        };
    }

    // class............................................................................................................

    @Override
    public Class<ConverterTextToLocale<ConverterContext>> type() {
        return Cast.to(ConverterTextToLocale.class);
    }
}
