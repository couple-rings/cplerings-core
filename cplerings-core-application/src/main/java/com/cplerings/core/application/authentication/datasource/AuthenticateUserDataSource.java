package com.cplerings.core.application.authentication.datasource;

import com.cplerings.core.domain.account.Account;

import java.util.Optional;

public interface AuthenticateUserDataSource {

    Optional<Account> getAuthenticatedAccount(String email);
}