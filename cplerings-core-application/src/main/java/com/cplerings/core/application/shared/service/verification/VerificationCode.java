package com.cplerings.core.application.shared.service.verification;

import lombok.Builder;

@Builder
public record VerificationCode(String code, FailedReason failedReason) {

    public enum FailedReason {

        INVALID_ARGUMENTS, ACCOUNT_NOT_IN_VERIFYING_STATUS
    }
}
