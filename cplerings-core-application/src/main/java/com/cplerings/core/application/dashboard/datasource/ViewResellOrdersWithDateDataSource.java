package com.cplerings.core.application.dashboard.datasource;

import com.cplerings.core.application.dashboard.input.ViewResellOrdersWithDateInput;
import com.cplerings.core.application.order.datasource.result.ResellOrders;
import com.cplerings.core.domain.account.Account;

public interface ViewResellOrdersWithDateDataSource {

    ResellOrders geResellOrders(ViewResellOrdersWithDateInput input, Long branchId);

    Account getAccountById(Long id);
}
