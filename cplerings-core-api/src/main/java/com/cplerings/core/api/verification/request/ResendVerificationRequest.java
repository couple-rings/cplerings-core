package com.cplerings.core.api.verification.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record ResendVerificationRequest (@NotBlank String email){}
