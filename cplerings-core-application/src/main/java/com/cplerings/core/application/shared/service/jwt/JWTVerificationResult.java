package com.cplerings.core.application.shared.service.jwt;

public interface JWTVerificationResult {

    Status getStatus();

    String getSubject();

    String getReason();

    enum Status {

        VALID, INVALID, EXPIRED
    }
}
