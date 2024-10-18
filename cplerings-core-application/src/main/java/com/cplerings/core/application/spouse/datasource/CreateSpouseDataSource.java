package com.cplerings.core.application.spouse.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.domain.spouse.SpouseAccount;

import java.util.Collection;
import java.util.Optional;

public interface CreateSpouseDataSource {

    boolean doesNotExistSpouseByCitizenIdIn(Collection<String> citizenIds);

    Optional<Account> getAccountById(Long id);

    Spouse save(Spouse spouse);

    void save(SpouseAccount account);
}
