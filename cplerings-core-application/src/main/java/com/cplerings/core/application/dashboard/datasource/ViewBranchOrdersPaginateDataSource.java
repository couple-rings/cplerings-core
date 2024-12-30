package com.cplerings.core.application.dashboard.datasource;

import com.cplerings.core.application.dashboard.datasource.data.CombinedOrders;
import com.cplerings.core.application.dashboard.input.ViewBranchOrdersPaginateInput;
import com.cplerings.core.domain.account.Account;

public interface ViewBranchOrdersPaginateDataSource {

    CombinedOrders getAllTypeOrders(ViewBranchOrdersPaginateInput input, Long branchId);

    Account getAccountById(Long accountId);
}
