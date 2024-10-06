package com.cplerings.core.test.component.jwt;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.application.shared.entity.account.ARole;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationResult;
import com.cplerings.core.application.shared.service.jwt.JWTVerificationService;
import com.cplerings.core.application.shared.service.jwt.input.JWTGenerationInput;
import com.cplerings.core.test.shared.AbstractCT;
import com.cplerings.core.test.shared.account.AccountTestConstant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class JWTVerificationServiceCT extends AbstractCT {

    @Autowired
    private JWTGenerationService jwtGenerationService;

    @Autowired
    private JWTVerificationService jwtVerificationService;

    @Test
    void givenJWTVerificationService_whenVerifyToken() {
        final JWTGenerationInput input = JWTGenerationInput.builder()
                .email(AccountTestConstant.CUSTOMER_EMAIL)
                .accountId(1L)
                .role(ARole.CUSTOMER)
                .build();
        final String token = jwtGenerationService.generateToken(input);
        final JWTVerificationResult result = jwtVerificationService.validateToken(token);

        thenResultIsOk(result);
    }

    private void thenResultIsOk(JWTVerificationResult result) {
        assertThat(result).isNotNull();
        assertThat(result.getStatus()).isEqualTo(JWTVerificationResult.Status.VALID);
        assertThat(result.getSubject()).isEqualTo(AccountTestConstant.CUSTOMER_EMAIL);
        assertThat(result.getReason()).isBlank();
    }

    @Test
    void givenJWTVerificationService_whenVerifyRefreshToken() {
        final JWTGenerationInput input = JWTGenerationInput.builder()
                .email(AccountTestConstant.CUSTOMER_EMAIL)
                .accountId(1L)
                .role(ARole.CUSTOMER)
                .build();
        final String token = jwtGenerationService.generateToken(input);
        final JWTVerificationResult result = jwtVerificationService.validateToken(token);

        thenResultIsOk(result);
    }
}
