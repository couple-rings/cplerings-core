package com.cplerings.core.application.spouse.datasource;

import java.util.List;
import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.spouse.Spouse;

public interface ViewSpousesOfCustomerDataSource {

    List<Spouse> getSpousesByCustomerId(Long customerId);
    Optional<Account> getCustomerById(Long customerId);
}
