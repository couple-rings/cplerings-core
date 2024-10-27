package com.cplerings.core.application.design.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.request.CustomRequest;
import com.cplerings.core.domain.design.session.DesignSession;

import java.util.Collection;
import java.util.Optional;

public interface ProcessDesignSessionPaymentDataSource {

    Optional<Account> getAccountById(Long accountId);

    Collection<DesignSession> saveAll(Collection<DesignSession> designSessions);

    CustomRequest save(CustomRequest customRequest);
}
