package com.cplerings.core.api.authentication.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record LoginCredentialRequest(@NotBlank String email, @NotBlank String password) {

}
