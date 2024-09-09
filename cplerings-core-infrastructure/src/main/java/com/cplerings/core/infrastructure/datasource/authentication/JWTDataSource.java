package com.cplerings.core.infrastructure.datasource.authentication;

import com.cplerings.core.domain.account.Account;

import java.util.Optional;

public interface JWTDataSource {

    Optional<Account> getAuthenticatedAccount(String email);
}
