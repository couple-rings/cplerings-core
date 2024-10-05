package com.cplerings.core.application.shared.service.jwt;

import com.cplerings.core.application.shared.service.jwt.input.JWTGenerationInput;

public interface JWTGenerationService {

    String generateToken(JWTGenerationInput input);

    String generateRefreshToken(JWTGenerationInput input);
}
