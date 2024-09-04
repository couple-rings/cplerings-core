package com.cplerings.core.infrastructure.service.jwt;

public interface JWTVerificationService {

    JWTVerificationResult validateToken(String token);

    JWTVerificationResult validateRefreshToken(String refreshToken);
}
