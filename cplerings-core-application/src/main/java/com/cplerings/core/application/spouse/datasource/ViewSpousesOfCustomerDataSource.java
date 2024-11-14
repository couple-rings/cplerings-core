package com.cplerings.core.application.spouse.datasource;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.spouse.Spouse;

public interface ViewSpousesOfCustomerDataSource {

    Optional<Spouse> getSpousesByCustomerId(Long customerId);
    Optional<Account> getCustomerById(Long customerId);
    List<Spouse> getSpouseByCoupleId(UUID coupleId);
}
