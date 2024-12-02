package com.cplerings.core.application.design.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.payment.DesignSessionPayment;

public interface CreateDesignSessionDataSource {

    Optional<Account> getAccountByEmail(String email);

    boolean thereIsNoUnusedDesignSession(Long accountId);

    DesignSessionPayment save(DesignSessionPayment designSessionPayment);
}
