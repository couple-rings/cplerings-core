package com.cplerings.core.application.dashboard.datasource;

import com.cplerings.core.application.dashboard.input.ViewTotalPaymentPerOrderInput;
import com.cplerings.core.application.dashboard.output.ViewTotalPaymentPerOrderOutput;
import com.cplerings.core.domain.account.Account;

public interface ViewTotalPaymentPerOrderDataSource {

    ViewTotalPaymentPerOrderOutput getTotalAmountPaymentWithOrderType(ViewTotalPaymentPerOrderInput input, Long branchId);

    Account getAccountById(Long accountId);
}
