package com.cplerings.core.application.design.datasource;

import com.cplerings.core.domain.account.Account;

import java.util.Optional;

public interface CheckRemainingDesignSessionDataSource {

    int getRemainingDesignSessionsCount(Long accountId);

    Optional<Account> getAccountByEmail(String email);
}
