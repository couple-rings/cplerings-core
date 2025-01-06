package com.cplerings.core.application.dashboard.datasource;

import com.cplerings.core.application.dashboard.output.ViewTotalExpenditureForAllOutput;
import com.cplerings.core.domain.account.Account;

public interface ViewTotalExpenditureForAllDataSource {

    ViewTotalExpenditureForAllOutput getTotalExpenditureForAll(Long branchId);

    Account getAccountById(Long id);
}
