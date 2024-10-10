package com.cplerings.core.application.verification.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;

public interface ResendVerificationDataSource {
    Optional<Account> getAccountByEmail(String email);

    void disableAllPreviousCodes(Long accountId);
}
