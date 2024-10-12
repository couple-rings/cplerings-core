package com.cplerings.core.application.account.datasource;

import com.cplerings.core.domain.account.Account;

import java.util.Optional;

public interface ResendCustomerVerificationCodeDataSource {

    Optional<Account> getAccountByEmail(String email);

    void disableAllPreviousCodes(Long accountId);
}
