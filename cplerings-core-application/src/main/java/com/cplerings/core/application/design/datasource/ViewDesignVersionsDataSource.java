package com.cplerings.core.application.design.datasource;

import java.util.Optional;

import com.cplerings.core.application.design.datasource.result.DesignVersions;
import com.cplerings.core.application.design.input.ViewDesignVersionsInput;
import com.cplerings.core.domain.account.Account;

public interface ViewDesignVersionsDataSource {

    Optional<Account> findAccountByEmail(String email);
    DesignVersions findDesignVersionsByCustomerId(Long customerId, ViewDesignVersionsInput input);
}
