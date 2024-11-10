package com.cplerings.core.application.design.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.design.request.CustomRequest;

import java.util.Optional;

public interface DetermineCustomRequestDataSource {

    Optional<CustomRequest> getCraftingRequestById(Long id);

    CustomRequest updateCraftingRequest(CustomRequest customRequest);

    Optional<Account> getStaff(Long staffId);
}
