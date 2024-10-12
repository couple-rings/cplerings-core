package com.cplerings.core.api.account.request;

import lombok.Builder;

import jakarta.validation.constraints.NotBlank;

@Builder
public record ResendCustomerVerificationCodeRequest(@NotBlank String email) {

}
