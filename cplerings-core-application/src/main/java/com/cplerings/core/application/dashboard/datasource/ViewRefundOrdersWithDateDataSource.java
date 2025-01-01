package com.cplerings.core.application.dashboard.datasource;

import com.cplerings.core.application.dashboard.input.ViewRefundOrdersWithDateInput;
import com.cplerings.core.application.order.datasource.result.Refunds;
import com.cplerings.core.domain.account.Account;

public interface ViewRefundOrdersWithDateDataSource {

    Refunds getRefundOrders(ViewRefundOrdersWithDateInput input, Long branchId);

    Account getAccountById(Long id);
}
