package com.cplerings.core.application.spouse.datasource;

import java.util.Optional;

import com.cplerings.core.application.spouse.input.VerifyResidentIdInput;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.spouse.Spouse;

public interface VerifyResidentIdDataSource {

    Optional<Spouse> getSpouseByCitizenId(VerifyResidentIdInput input);

    Optional<Account> getCustomerWithSpouseById(Long customerId);
}
