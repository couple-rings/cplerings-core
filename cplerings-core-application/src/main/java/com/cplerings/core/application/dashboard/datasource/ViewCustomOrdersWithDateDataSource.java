package com.cplerings.core.application.dashboard.datasource;

import com.cplerings.core.application.dashboard.input.ViewCustomOrdersWithDateInput;
import com.cplerings.core.application.order.datasource.result.CustomOrders;
import com.cplerings.core.domain.account.Account;

public interface ViewCustomOrdersWithDateDataSource {

    CustomOrders getCustomOrders(ViewCustomOrdersWithDateInput input, Long branchId);

    Account getAccountById(Long id);
}
