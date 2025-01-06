package com.cplerings.core.application.dashboard.datasource;

import java.time.Instant;

import com.cplerings.core.application.dashboard.output.ViewTotalExpenditureOutput;
import com.cplerings.core.domain.account.Account;

public interface ViewTotalExpenditureDataSource {

    ViewTotalExpenditureOutput getTotalExpenditure(Instant start, Instant end, Long branchId);

    Account getAccountById(Long id);
}
