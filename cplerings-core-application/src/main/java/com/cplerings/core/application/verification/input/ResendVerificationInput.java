package com.cplerings.core.application.verification.input;

import lombok.Builder;

@Builder
public record ResendVerificationInput (String email) {
}
