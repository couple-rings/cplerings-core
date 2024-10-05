package com.cplerings.core.application.shared.service.jwt.input;

import com.cplerings.core.application.shared.entity.ARole;

import lombok.Builder;

@Builder
public record JWTGenerationInput(String email, ARole role, Long accountId) {

}
