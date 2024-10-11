package com.cplerings.core.application.account.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;

public interface ViewProfileDataSource {

    Optional<Account> getAccountById(Long id);
}
