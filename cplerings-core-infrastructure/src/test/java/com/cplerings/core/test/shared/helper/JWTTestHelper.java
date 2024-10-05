package com.cplerings.core.test.shared.helper;

import com.cplerings.core.application.shared.mapper.ARoleMapper;
import com.cplerings.core.application.shared.service.jwt.JWTGenerationService;
import com.cplerings.core.application.shared.service.jwt.input.JWTGenerationInput;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.infrastructure.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

import java.util.Optional;

@TestComponent
@RequiredArgsConstructor
public final class JWTTestHelper {

    private final JWTGenerationService jwtGenerationService;
    private final AccountRepository accountRepository;
    private final ARoleMapper aRoleMapper;

    public String generateToken(String email) {
        final JWTGenerationInput input = extractIntoJWTGenerationInput(email);
        return jwtGenerationService.generateToken(input);
    }

    private JWTGenerationInput extractIntoJWTGenerationInput(String email) {
        Optional<Account> account = accountRepository.findByEmail(email);
        if (account.isEmpty()) {
            throw new IllegalArgumentException("Account with email not found");
        }
        return JWTGenerationInput.builder()
                .email(email)
                .role(aRoleMapper.toRole(account.get().getRole()))
                .accountId(account.get().getId())
                .build();
    }

    public String generateRefreshToken(String email) {
        final JWTGenerationInput input = extractIntoJWTGenerationInput(email);
        return jwtGenerationService.generateRefreshToken(input);
    }
}
