package com.cplerings.core.infrastructure.service.jwt;

public interface JWTVerificationResult {

    enum Status {

        VALID, INVALID, EXPIRED
    }

    Status getStatus();

    String getSubject();

    String getReason();
}
