package com.cplerings.core.infrastructure.datasource.authentication;

import java.util.Optional;

import com.cplerings.core.application.authentication.datasource.LoginDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.infrastructure.datasource.DataSource;

@DataSource
public class DefaultLoginDataSource implements LoginDataSource {

    @Override
    public Optional<Account> getLoginAccount(String email) {
        return Optional.empty();
    }
}
