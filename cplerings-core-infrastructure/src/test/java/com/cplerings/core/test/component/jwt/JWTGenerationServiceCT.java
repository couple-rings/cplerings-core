package com.cplerings.core.test.component.jwt;

import static org.assertj.core.api.Assertions.assertThat;

import com.cplerings.core.application.shared.entity.account.ARole;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.jwt.input.JWTGenerationInput;
import com.cplerings.core.test.shared.AbstractCT;
import com.cplerings.core.test.shared.account.AccountTestConstant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

class JWTGenerationServiceCT extends AbstractCT {

    @Autowired
    private JWTGenerationService jwtGenerationService;

    @Value("${spring.application.name}")
    private String appName;

    @Test
    void givenJWTGenerationService_whenGenerateToken() {
        final JWTGenerationInput input = JWTGenerationInput.builder()
                .email(AccountTestConstant.CUSTOMER_EMAIL)
                .accountId(1L)
                .role(ARole.CUSTOMER)
                .build();
        final String token = jwtGenerationService.generateToken(input);

        thenTokenIsReturned(token);
        thenTokenContainsRequiredClaims(token);
    }

    private void thenTokenIsReturned(String token) {
        assertThat(token).isNotBlank();
    }

    private void thenTokenContainsRequiredClaims(String token) {
        final String payload = token.split("\\.")[1];
        assertThat(payload).isNotBlank();

        final String data = new String(Base64.getUrlDecoder().decode(payload), StandardCharsets.UTF_8);
        assertThat(data).isNotBlank();
        assertThat(data).contains("\"sub\":\"" + AccountTestConstant.CUSTOMER_EMAIL + "\"");
        assertThat(data).contains("\"id\":\"1\"");
        assertThat(data).contains("\"role\":\"" + ARole.CUSTOMER + "\"");
        assertThat(data).contains("\"iss\":\"" + appName + "\"");
    }

    @Test
    void givenJWTGenerationService_whenGenerateRefreshToken() {
        final JWTGenerationInput input = JWTGenerationInput.builder()
                .email(AccountTestConstant.CUSTOMER_EMAIL)
                .accountId(1L)
                .role(ARole.CUSTOMER)
                .build();
        final String token = jwtGenerationService.generateRefreshToken(input);

        thenTokenIsReturned(token);
        thenTokenContainsRequiredClaims(token);
    }
}
