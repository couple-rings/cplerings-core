package com.cplerings.core.application.design.datasource;

import java.util.List;
import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.DesignSession;

public interface CreateDesignSessionDataSource {

    void createDesignSession(List<DesignSession> designSession);
    Optional<Account> getAccountById(Long id);
}
