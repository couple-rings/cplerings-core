package com.cplerings.core.application.design.datasource;

import com.cplerings.core.application.design.datasource.result.CustomRequests;
import com.cplerings.core.application.design.input.ViewCustomRequestsInput;
import com.cplerings.core.domain.account.Account;

import java.util.Optional;

public interface ViewCustomRequestsDataSource {

    CustomRequests getCustomRequests(ViewCustomRequestsInput input);

    Optional<Account> getCustomerById(Long customerId);
}
