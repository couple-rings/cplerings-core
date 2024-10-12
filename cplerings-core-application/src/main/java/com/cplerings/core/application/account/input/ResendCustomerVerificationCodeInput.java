package com.cplerings.core.application.account.input;

import lombok.Builder;

@Builder
public record ResendCustomerVerificationCodeInput(String email) {
}
