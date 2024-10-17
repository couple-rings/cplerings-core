package com.cplerings.core.api.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NoRequest {

    @SuppressWarnings("InstantiationOfUtilityClass")
    public static final NoRequest INSTANCE = new NoRequest();
}
