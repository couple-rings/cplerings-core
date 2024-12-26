package com.cplerings.core.application.dashboard.datasource;

import java.time.Instant;

import com.cplerings.core.application.dashboard.datasource.data.Revenue;
import com.cplerings.core.domain.account.Account;

public interface ViewBranchRevenueDataSource {

    Revenue getTotalRevenue(Instant start, Instant end, Long branchId);

    Account getAccountById(Long id);
}
