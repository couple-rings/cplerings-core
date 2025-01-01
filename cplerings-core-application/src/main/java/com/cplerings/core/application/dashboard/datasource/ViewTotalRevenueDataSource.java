package com.cplerings.core.application.dashboard.datasource;

import java.math.BigDecimal;

import com.cplerings.core.domain.account.Account;

public interface ViewTotalRevenueDataSource {

    BigDecimal getTotalRevenue(Long branchId);

    Account getAccountById(Long accountId);
}
