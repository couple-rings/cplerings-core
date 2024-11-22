package com.cplerings.core.test.shared.helper;

import com.cplerings.core.application.shared.service.password.PasswordService;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountStatus;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.domain.shared.AbstractEntity;
import com.cplerings.core.domain.shared.Auditor;
import com.cplerings.core.test.shared.datasource.TestDataSource;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.test.context.TestComponent;

import java.util.Objects;
import java.util.UUID;

@TestComponent
@RequiredArgsConstructor
public class AccountTestHelper {

    private static final Auditor TEST_AUDITOR = (() -> "CoupleRings-test");

    private final PasswordService passwordService;
    private final TestDataSource testDataSource;

    public void updateAuditor(AbstractEntity entity) {
        Objects.requireNonNull(entity);
        if (entity.getId() == null || entity.getId() <= 0) {
            entity.setCreator(TEST_AUDITOR);
        }
        entity.updateModifier(TEST_AUDITOR);
    }

    public Account createAccount(Role role) {
        Account account = Account.builder()
                .email(UUID.randomUUID() + "@cplerings.com")
                .username(UUID.randomUUID().toString())
                .password(passwordService.encryptPassword(UUID.randomUUID().toString()))
                .role(role)
                .status(AccountStatus.ACTIVE)
                .build();
        return testDataSource.save(account);
    }
}
