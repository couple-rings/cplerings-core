package com.cplerings.core.application.shared.service.verification;

import lombok.Builder;

@Builder
public record VerificationResult(Status status) {

    public enum Status {

        VALID, INVALID, EXPIRED
    }
}
