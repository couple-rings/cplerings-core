package com.cplerings.core.application.design.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.CustomDesign;
import com.cplerings.core.domain.design.DesignVersion;
import com.cplerings.core.domain.spouse.Spouse;

import java.util.Optional;

public interface CreateCustomDesignDataSource {

    CustomDesign save(CustomDesign customDesign);

    Optional<Account> getCustomerById(long customerId);

    Optional<Spouse> getSpouseById(long spouseId);

    Optional<DesignVersion> getDesignVersionById(long designVersionId);

    Optional<CustomDesign> getCustomDesignBySpouseId(long spouseId);

    Optional<CustomDesign> getCustomDesignByDesignVersionId(long designVersionId);
}
