package com.cplerings.core.application.account.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountPasswordReset;

import java.util.Optional;

public interface ResetPasswordDataSource {

    Optional<Account> findAccountByEmailWithMostRecentResetPasswordOTP(String email);

    void save(AccountPasswordReset accountPasswordReset);

    Account save(Account account);
}
