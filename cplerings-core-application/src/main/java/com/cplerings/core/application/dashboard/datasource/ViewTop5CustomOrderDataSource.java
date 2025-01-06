package com.cplerings.core.application.dashboard.datasource;

import java.util.List;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.order.CustomOrder;

public interface ViewTop5CustomOrderDataSource {

    List<CustomOrder> getTop5CustomOrders(Long branchId);

    Account getAccountById(Long id);
}
