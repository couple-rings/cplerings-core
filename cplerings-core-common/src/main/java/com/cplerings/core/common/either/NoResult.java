package com.cplerings.core.common.either;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NoResult {

    @SuppressWarnings("InstantiationOfUtilityClass")
    public static final NoResult INSTANCE = new NoResult();
}
