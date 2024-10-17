package com.cplerings.core.api.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NoData {

    @SuppressWarnings("InstantiationOfUtilityClass")
    public static final NoData INSTANCE = new NoData();
}
