package com.cplerings.core.application.account.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountVerification;

import java.util.Optional;

public interface VerifyCustomerDataSource {

    Optional<Account> getAccountByEmailWithMostRecentVerificationCode(String email);

    void saveAccountVerification(AccountVerification accountVerification);

    Account saveAccount(Account account);
}
