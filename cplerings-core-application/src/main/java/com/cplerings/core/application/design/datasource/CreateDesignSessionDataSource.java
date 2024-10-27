package com.cplerings.core.application.design.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.payment.PaymentReceiver;

import java.util.Optional;

public interface CreateDesignSessionDataSource {

    Optional<Account> getAccountByEmail(String email);

    boolean thereIsNoUnusedDesignSession(Long accountId);

    PaymentReceiver save(PaymentReceiver paymentReceiver);
}
