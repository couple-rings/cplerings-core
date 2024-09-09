package com.cplerings.core.infrastructure.locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

import java.util.Locale;

@Configuration
public class LocaleConfiguration {

    private static final Locale DEFAULT_LOCALE = Locale.ENGLISH;

    @Value("${cplerings.locale}")
    private String locale;

    @PostConstruct
    public void setUpLocale() {
        if (StringUtils.isNotBlank(locale)) {
            Locale.setDefault(Locale.of(locale));
        } else {
            Locale.setDefault(DEFAULT_LOCALE);
        }
    }
}
