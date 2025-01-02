package com.cplerings.core.application.dashboard.datasource;

import com.cplerings.core.domain.account.Account;

public interface ViewTotalTransactionsOfBranchDataSource {

    Long getTotalTransaction(Long branchId);

    Account getAccountById(Long accountId);
}
