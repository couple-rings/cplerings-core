package com.cplerings.core.application.shared.service.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConfigurationKey {

    /**
     * Design fee
     */
    DEFE("DEFE"),

    /**
     * Side diamond price
     */
    SDPR("SDPR"),

    /**
     * Crafting stage progress 1
     */
    CSP1("CSP1"),

    /**
     * Crafting stage progress 2
     */
    CSP2("CSP2"),

    /**
     * Crafting stage progress 3
     */
    CSP3("CSP3"),
    ;

    private final String key;
}
