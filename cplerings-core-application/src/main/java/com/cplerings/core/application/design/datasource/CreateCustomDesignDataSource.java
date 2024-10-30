package com.cplerings.core.application.design.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.spouse.Spouse;

public interface CreateCustomDesignDataSource {

    CustomDesign save(CustomDesign customDesign);
    Optional<Account> getCustomerById(long customerId);
    Optional<Spouse> getSpouseById(long spouseId);
    Optional<DesignVersion> getDesignVersionById(long designVersionId);
}
