package com.cplerings.core.application.shared.service.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ConfigurationKey {

    DEFE("DEFE");

    private final String key;
}
