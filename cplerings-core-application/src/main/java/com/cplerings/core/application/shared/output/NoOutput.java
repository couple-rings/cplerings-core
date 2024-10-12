package com.cplerings.core.application.shared.output;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public final class NoOutput {

    @SuppressWarnings("InstantiationOfUtilityClass")
    public static final NoOutput INSTANCE = new NoOutput();
}
