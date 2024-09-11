package com.cplerings.core.api.authentication.request;

import lombok.Builder;

import jakarta.validation.constraints.NotBlank;

@Builder
public record LoginCredentialRequest(@NotBlank String email, @NotBlank String password) {

}
