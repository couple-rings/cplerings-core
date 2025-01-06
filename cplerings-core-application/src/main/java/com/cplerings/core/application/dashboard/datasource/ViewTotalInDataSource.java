package com.cplerings.core.application.dashboard.datasource;

import java.time.Instant;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.shared.valueobject.Money;

public interface ViewTotalInDataSource {

    Money getTotal(Instant start, Instant end, Long branchId);

    Account getAccountById(Long id);
}
