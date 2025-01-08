package com.cplerings.core.application.dashboard.datasource;

import com.cplerings.core.application.dashboard.input.ViewTotalPaymentPerOrderInput;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.shared.valueobject.Money;

public interface ViewTotalPaymentPerOrderDataSource {

    Money getTotalAmountPaymentWithOrderType(ViewTotalPaymentPerOrderInput input, Long branchId);

    Account getAccountById(Long accountId);
}
