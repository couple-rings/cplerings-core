package com.cplerings.core.application.dashboard.datasource;

import java.time.Instant;

import com.cplerings.core.application.dashboard.datasource.data.Orders;
import com.cplerings.core.domain.account.Account;

public interface ViewBranchOrdersDataSource {

    Orders getOrders(Instant start, Instant end, Long branchId);

    Account getAccountById(Long id);
}
