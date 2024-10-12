package com.cplerings.core.application.account.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountPasswordReset;

public interface ResetPasswordDataSource {

    Optional<Account> findAccountByEmailWithMostRecentResetPasswordOTP(String email);

    void save(AccountPasswordReset accountPasswordReset);

    Account save(Account account);
}
