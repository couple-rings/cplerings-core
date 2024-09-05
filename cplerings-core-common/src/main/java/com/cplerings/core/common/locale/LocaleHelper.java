package com.cplerings.core.common.locale;

import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocaleHelper {

    private static final String LOCALE_PATH = "locale/main";

    public static String translateLocale(String key, Locale locale) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("Locale key is blank");
        }
        Objects.requireNonNull(locale, "Locale is null");
        final ResourceBundle resourceBundle = ResourceBundle.getBundle(LOCALE_PATH, locale);
        return resourceBundle.getString(key);
    }

    public static String translateLocale(String key) {
        return translateLocale(key, Locale.getDefault());
    }
}
