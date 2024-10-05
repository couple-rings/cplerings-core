package com.cplerings.core.infrastructure.service.jwt;

import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationResult;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationService;
import com.cplerings.core.application.shared.service.jwt.input.JWTGenerationInput;
import com.cplerings.core.common.temporal.TemporalUtils;
import com.cplerings.core.domain.account.Role;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.transaction.Transactional;

import java.time.Instant;

@Service
@Transactional(rollbackOn = Exception.class)
public class DefaultJWTService implements JWTGenerationService, JWTVerificationService {

    private static final String SUBJECT_CLAIM = "sub";
    private static final String ID_CLAIM = "id";
    private static final String ROLE_CLAIM = "role";

    @Value("${cplerings.jwt.secret}")
    private String secret;

    @Value("${cplerings.jwt.duration}")
    private long tokenDuration;

    @Value("${cplerings.jwt.refreshDuration}")
    private long refreshDuration;

    @Value("${spring.application.name}")
    private String issuer;

    @Override
    public String generateToken(JWTGenerationInput input) {
        return internalGenerateToken(input, tokenDuration);
    }

    @Override
    public String generateRefreshToken(JWTGenerationInput input) {
        return internalGenerateToken(input, refreshDuration);
    }

    private String internalGenerateToken(JWTGenerationInput input, long durationSecond) {
        final Instant issuedAt = TemporalUtils.getCurrentInstantUTC();
        return JWT.create()
                .withSubject(input.email())
                .withClaim(ID_CLAIM, String.valueOf(input.accountId()))
                .withClaim(ROLE_CLAIM, input.role().name())
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
                    .withClaimPresence(ID_CLAIM)
                    .withClaimPresence(ROLE_CLAIM)
                    .build();
            final DecodedJWT decodedJWT = verifier.verify(token);
            final Instant issuedAt = decodedJWT.getIssuedAtAsInstant();
            final Instant expiredAt = decodedJWT.getExpiresAtAsInstant();
            if (expiredAt.compareTo(issuedAt.plusSeconds(duration)) != 0) {
                throw new JWTVerificationException("Duration between issued at and expired at is invalid");
            }
            final long id = Long.parseLong(decodedJWT.getClaim(ID_CLAIM).asString());
            if (id <= 0) {
                throw new JWTVerificationException("ID is invalid");
            }
            final String role = decodedJWT.getClaim(ROLE_CLAIM).asString();
            if (StringUtils.isBlank(role) || Role.isRoleAsStringNotValid(role)) {
                throw new JWTVerificationException("Role is invalid");
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
