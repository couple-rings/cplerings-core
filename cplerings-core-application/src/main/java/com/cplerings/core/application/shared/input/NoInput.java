package com.cplerings.core.application.shared.input;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class NoInput {

    @SuppressWarnings("InstantiationOfUtilityClass")
    public static final NoInput INSTANCE = new NoInput();
}
