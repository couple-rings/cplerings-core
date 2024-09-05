package com.cplerings.core.test.integration.helper;

import org.springframework.boot.test.context.TestComponent;

import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;

import lombok.RequiredArgsConstructor;

@TestComponent
@RequiredArgsConstructor
public final class JWTTestHelper {

    private final JWTGenerationService jwtGenerationService;

    public String generateToken(String email) {
        return jwtGenerationService.generateToken(email);
    }
}
