package com.cplerings.core.api.authentication.request;

import lombok.Builder;

@Builder
public record LoginCredentialRequest(String email, String password) {

}
