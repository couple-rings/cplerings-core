package com.cplerings.core.application.account.datasource;

import com.cplerings.core.domain.account.Account;

public interface RegisterCustomerDataSource {

    boolean emailIsNew(String email);

    boolean usernameIsNew(String username);

    Account save(Account account);
}
