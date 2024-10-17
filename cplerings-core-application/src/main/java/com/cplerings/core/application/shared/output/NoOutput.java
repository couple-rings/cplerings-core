package com.cplerings.core.application.shared.output;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NoOutput {

    @SuppressWarnings("InstantiationOfUtilityClass")
    public static final NoOutput INSTANCE = new NoOutput();
}
