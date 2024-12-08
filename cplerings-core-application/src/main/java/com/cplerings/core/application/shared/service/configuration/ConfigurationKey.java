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

    /**
     * Maximum maintenance duration
     */
    MMAD("MMAD"),

    /**
     * Price Application Ratio
     */
    PARA("PARA"),

    /**
     * Crafting fee
     */
    CRFE("CRFE"),

    /**
     * Shipping fee
     */
    SHFE("SHFE"),

    /**
     * Refund
     */
    REFU("REFU"),

    /**
     * Resell
     */
    RESE("RESE"),
    ;

    private final String key;
}
