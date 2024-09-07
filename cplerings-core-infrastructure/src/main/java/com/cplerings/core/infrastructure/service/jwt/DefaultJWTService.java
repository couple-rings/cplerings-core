package com.cplerings.core.infrastructure.service.jwt;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationResult;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationService;
import com.cplerings.core.common.temporal.TemporalUtils;

import jakarta.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class DefaultJWTService implements JWTGenerationService, JWTVerificationService {

    private static final String SUBJECT_CLAIM = "sub";

    @Value("${cplerings.jwt.secret}")
    private String secret;

    @Value("${cplerings.jwt.duration}")
    private long tokenDuration;

    @Value("${cplerings.jwt.refreshDuration}")
    private long refreshDuration;

    @Value("${spring.application.name}")
    private String issuer;

    @Override
    public String generateToken(String email) {
        return internalGenerateToken(email, tokenDuration);
    }

    @Override
    public String generateRefreshToken(String email) {
        return internalGenerateToken(email, refreshDuration);
    }

    private String internalGenerateToken(String email, long durationSecond) {
        final Instant issuedAt = TemporalUtils.getCurrentInstantUTC();
        return JWT.create()
                .withSubject(email)
                .withIssuer(issuer)
                .withIssuedAt(issuedAt)
                .withExpiresAt(issuedAt.plusSeconds(durationSecond))
                .sign(Algorithm.HMAC256(secret));
    }

    @Override
    public JWTVerificationResult validateToken(String token) {
        return internalValidateToken(token, tokenDuration);
    }

    @Override
    public JWTVerificationResult validateRefreshToken(String refreshToken) {
        return internalValidateToken(refreshToken, refreshDuration);
    }

    private JWTVerificationResult internalValidateToken(String token, long duration) {
        try {
            final JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer(issuer)
                    .withClaimPresence(SUBJECT_CLAIM)
                    .build();
            final DecodedJWT decodedJWT = verifier.verify(token);
            final Instant issuedAt = decodedJWT.getIssuedAtAsInstant();
            final Instant expiredAt = decodedJWT.getExpiresAtAsInstant();
            if (expiredAt.compareTo(issuedAt.plusSeconds(duration)) != 0) {
                throw new JWTVerificationException("Duration between issued at and expired at is invalid");
            }
            return DefaultJWTVerificationResult.builder()
                    .status(JWTVerificationResult.Status.VALID)
                    .decodedJWT(decodedJWT)
                    .build();
        } catch (TokenExpiredException e) {
            return DefaultJWTVerificationResult.builder()
                    .status(JWTVerificationResult.Status.EXPIRED)
                    .decodedJWT(null)
                    .reason(e.getMessage())
                    .build();
        } catch (Exception e) {
            return DefaultJWTVerificationResult.builder()
                    .status(JWTVerificationResult.Status.INVALID)
                    .decodedJWT(null)
                    .reason(e.getMessage())
                    .build();
        }
    }
}
