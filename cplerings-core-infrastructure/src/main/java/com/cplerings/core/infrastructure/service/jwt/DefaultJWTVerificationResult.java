package com.cplerings.core.infrastructure.service.jwt;

import org.apache.commons.lang3.StringUtils;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationResult;

import lombok.Builder;

@Builder
public record DefaultJWTVerificationResult(Status status, DecodedJWT decodedJWT, String reason)
        implements JWTVerificationResult {

    public DefaultJWTVerificationResult {
        if (status == null) {
            throw new IllegalArgumentException("Status is null");
        }
        if (status == Status.VALID) {
            if (decodedJWT == null || StringUtils.isNotBlank(reason)) {
                throw new IllegalArgumentException("Decoded JWT must be set for case Status = VALID");
            }
        } else if (decodedJWT != null || StringUtils.isBlank(reason)) {
            throw new IllegalArgumentException("Decoded JWT must not be set for all invalid cases");
        }
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String getSubject() {
        return decodedJWT().getSubject();
    }

    @Override
    public String getReason() {
        return reason;
    }
}
