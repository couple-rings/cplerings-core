package com.cplerings.core.application.spouse.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.spouse.Spouse;
import com.cplerings.core.domain.spouse.SpouseAccount;

public interface CreateSpouseDataSource {

    Optional<Spouse> getSpouseByCitizenId(String citizenId);
    Optional<Account> getAccountById(Long id);
    Spouse save(Spouse spouse);
    void save(SpouseAccount account);
}
