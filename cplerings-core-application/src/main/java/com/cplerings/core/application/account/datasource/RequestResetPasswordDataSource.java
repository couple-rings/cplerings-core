package com.cplerings.core.application.account.datasource;

import com.cplerings.core.domain.account.Account;

import java.util.Optional;

public interface RequestResetPasswordDataSource {

    Optional<Account> findByEmail(String email);
}
