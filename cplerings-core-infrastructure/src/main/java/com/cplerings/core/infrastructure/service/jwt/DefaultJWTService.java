package com.cplerings.core.infrastructure.service.jwt;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.cplerings.core.application.shared.service.jwt.JWTService;
import com.cplerings.core.common.temporal.TemporalHelper;

import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class DefaultJWTService implements JWTService {

    @Value("${cplerings.jwt.secret}")
    private String secret;

    @Value("${cplerings.jwt.duration}")
    private long tokenDuration;

    @Value("${cplerings.jwt.refreshDuration}")
    private long refreshDuration;

    @Override
    public String generateToken(String email) {
        return internalGenerateToken(email, tokenDuration);
    }

    @Override
    public String generateRefreshToken(String email) {
        return internalGenerateToken(email, refreshDuration);
    }

    private String internalGenerateToken(String email, long durationSecond) {
        final Instant issuedAt = TemporalHelper.getCurrentInstantUTC();
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(issuedAt)
                .withExpiresAt(issuedAt.plusSeconds(durationSecond))
                .sign(Algorithm.HMAC256(secret));
    }
}
