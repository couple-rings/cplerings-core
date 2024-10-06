package com.cplerings.core.infrastructure.datasource.account;

import com.cplerings.core.application.account.datasource.RegisterCustomerDataSource;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.infrastructure.datasource.AbstractDataSource;
import com.cplerings.core.infrastructure.datasource.DataSource;
import com.cplerings.core.infrastructure.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@DataSource
@RequiredArgsConstructor
public class SharedAccountDataSource extends AbstractDataSource implements RegisterCustomerDataSource {

    private final AccountRepository accountRepository;

    @Override
    public boolean emailIsNew(String email) {
        return !accountRepository.existsByEmail(email);
    }

    @Override
    public boolean usernameIsNew(String username) {
        return !accountRepository.existsByUsername(username);
    }

    @Override
    public Account save(Account account) {
        updateAuditor(account);
        return accountRepository.save(account);
    }
}
