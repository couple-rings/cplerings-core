package com.cplerings.core.application.design.datasource;

import com.cplerings.core.application.design.datasource.result.DesignVersions;
import com.cplerings.core.application.design.input.ViewDesignVersionsInput;
import com.cplerings.core.domain.account.Account;

import java.util.Optional;

public interface ViewDesignVersionsDataSource {

    Optional<Account> findAccountByEmail(String email);

    DesignVersions findDesignVersionsByCustomerId(Long customerId, ViewDesignVersionsInput input);

    DesignVersions findDesignVersions(ViewDesignVersionsInput input);
}
