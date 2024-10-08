package com.cplerings.core.application.account.datasource;

import com.cplerings.core.domain.account.Account;

import java.util.Optional;

public interface VerifyCustomerDataSource {

    Optional<Account> getAccountByEmailWithMostRecentVerificationCode(String email);
}
