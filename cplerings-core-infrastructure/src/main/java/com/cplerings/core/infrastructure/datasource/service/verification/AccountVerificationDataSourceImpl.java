package com.cplerings.core.infrastructure.datasource.service.verification;

import com.cplerings.core.domain.account.AccountVerification;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.AccountVerificationRepository;
import com.cplerings.core.infrastructure.service.verification.AccountVerificationDataSource;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class AccountVerificationDataSourceImpl extends AbstractDataSource implements AccountVerificationDataSource {

    private final AccountVerificationRepository accountVerificationRepository;

    @Override
    public AccountVerification save(AccountVerification accountVerification) {
        updateAuditor(accountVerification);
        return accountVerificationRepository.save(accountVerification);
    }
}
