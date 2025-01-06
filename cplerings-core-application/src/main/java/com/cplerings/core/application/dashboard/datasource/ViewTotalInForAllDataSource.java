package com.cplerings.core.application.dashboard.datasource;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.shared.valueobject.Money;

public interface ViewTotalInForAllDataSource {

    Money getTotalForAll(Long branchId);

    Account getAccountById(Long id);
}
