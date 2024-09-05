package com.cplerings.core.test.integration.helper;

import java.util.Objects;

import org.springframework.boot.test.context.TestComponent;

import com.cplerings.core.application.shared.service.password.PasswordService;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.infrastructure.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@TestComponent
@RequiredArgsConstructor
public class AccountTestHelper {

    public static final String DEFAULT_EMAIL = "test@test.com";
    public static final String DEFAULT_PASSWORD = "P@ssw0rd";
    public static final String DEFAULT_CREATED_BY = "test";

    private final PasswordService passwordService;
    private final AccountRepository accountRepository;

    public Account createCustomer() {
        return createOneWithRole(Role.CUSTOMER);
    }

    public Account createOneWithRole(Role role) {
        Objects.requireNonNull(role, "Role is null");
        final Account account = Account.builder()
                .email(DEFAULT_EMAIL)
                .password(passwordService.encryptPassword(DEFAULT_PASSWORD))
                .role(role)
                .createdBy(DEFAULT_CREATED_BY)
                .build();
        return accountRepository.save(account);
    }
}
