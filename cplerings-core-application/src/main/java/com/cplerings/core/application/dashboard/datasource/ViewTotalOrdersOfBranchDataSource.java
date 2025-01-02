package com.cplerings.core.application.dashboard.datasource;

import com.cplerings.core.domain.account.Account;

public interface ViewTotalOrdersOfBranchDataSource {

    Long getTotalOrders(Long branchId);

    Account getAccountById(Long accountId);
}
