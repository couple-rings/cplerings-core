package com.cplerings.core.application.shared.service.jwt;

public interface JWTVerificationResult {

    enum Status {

        VALID, INVALID, EXPIRED
    }

    Status getStatus();

    String getSubject();

    String getReason();
}
