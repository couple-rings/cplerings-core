package com.cplerings.core.application.shared.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class SessionInformation {

    public static final SessionInformation DEFAULT = SessionInformation.builder().build();

    private static final boolean DEFAULT_READ_ONLY = false;
    private static final int DEFAULT_TIMEOUT = 30;

    @Builder.Default
    private boolean readOnly = DEFAULT_READ_ONLY;

    @Builder.Default
    private int timeout = DEFAULT_TIMEOUT;

    @Builder.Default
    private SessionPropagation propagation = SessionPropagation.REQUIRED;
}
