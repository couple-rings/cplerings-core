package com.cplerings.core.infrastructure.datasource.service.verification;

import java.util.Optional;

import com.cplerings.core.application.verification.datasource.ResendVerificationDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.AccountVerificationRepository;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class ResendVerificationDataSourceImpl extends AbstractDataSource implements ResendVerificationDataSource {

    private final AccountVerificationRepository accountVerificationRepository;
    private static final QAccount Q_ACCOUNT = QAccount.account;

    @Override
    public Optional<Account> getAccountByEmail(String email) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.email.eq(email))
                .fetchOne());
    }

    @Override
    public void disableAllPreviousCodes(Long accountId) {
        accountVerificationRepository.disableVerificationCodes(accountId);
    }
}
