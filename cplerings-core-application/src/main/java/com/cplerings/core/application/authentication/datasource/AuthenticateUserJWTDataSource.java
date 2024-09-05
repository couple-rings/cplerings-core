package com.cplerings.core.application.authentication.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;

public interface AuthenticateUserJWTDataSource {

    Optional<Account> getAuthenticatedAccount(String email);
}
