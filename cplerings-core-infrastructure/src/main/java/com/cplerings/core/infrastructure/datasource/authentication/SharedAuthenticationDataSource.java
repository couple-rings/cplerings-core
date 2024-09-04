package com.cplerings.core.infrastructure.datasource.authentication;

import java.util.Optional;

import com.cplerings.core.application.authentication.datasource.LoginDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.QAccount;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;

@DataSource
public class SharedAuthenticationDataSource extends AbstractDataSource implements LoginDataSource, JWTDataSource {

    private static final QAccount Q_ACCOUNT = QAccount.account;

    @Override
    public Optional<Account> getLoginAccount(String email) {
        return internalGetAccountByEmail(email);
    }

    @Override
    public Optional<Account> getAuthenticatedAccount(String email) {
        return internalGetAccountByEmail(email);
    }

    private Optional<Account> internalGetAccountByEmail(String email) {
        return Optional.ofNullable(createQuery()
                .select(Q_ACCOUNT)
                .from(Q_ACCOUNT)
                .where(Q_ACCOUNT.email.eq(email))
                .fetchOne());
    }
}
