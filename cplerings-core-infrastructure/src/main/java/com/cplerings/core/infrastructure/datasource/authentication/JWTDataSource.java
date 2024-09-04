package com.cplerings.core.infrastructure.datasource.authentication;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;

public interface JWTDataSource {

    Optional<Account> getAuthenticatedAccount(String email);
}
