package com.cplerings.core.application.shared.service.jwt;

public interface JWTVerificationService {

    JWTVerificationResult validateToken(String token);

    JWTVerificationResult validateRefreshToken(String refreshToken);
}
