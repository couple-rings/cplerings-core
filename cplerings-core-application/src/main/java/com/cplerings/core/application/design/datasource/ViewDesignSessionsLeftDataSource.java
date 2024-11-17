package com.cplerings.core.application.design.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;

public interface ViewDesignSessionsLeftDataSource {

    int getRemainingDesignSessionsCount(Long customerId);

    Optional<Account> getCustomerById(Long customerId);
}
