package com.cplerings.core.application.shared.service.jwt;

public interface JWTGenerationService {

    String generateToken(String email);

    String generateRefreshToken(String email);
}
