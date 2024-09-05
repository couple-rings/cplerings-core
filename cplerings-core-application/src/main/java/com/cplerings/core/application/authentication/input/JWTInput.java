package com.cplerings.core.application.authentication.input;

import lombok.Builder;

@Builder
public record JWTInput(String token) {

}
