package com.cplerings.core.api.verification.data;

import lombok.Builder;

@Builder
public record ResendVerification(String email) {
}
