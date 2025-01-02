package com.cplerings.core.application.dashboard.datasource;

import com.cplerings.core.application.dashboard.input.ViewTotalTypeOfPaymentInput;
import com.cplerings.core.application.dashboard.output.ViewTotalTypeOfPaymentOutput;
import com.cplerings.core.domain.account.Account;

public interface ViewTotalTypeOfPaymentDataSource {

    ViewTotalTypeOfPaymentOutput getTotalTypeOfPayment(ViewTotalTypeOfPaymentInput input, Long branchId);

    Account getAccountById(Long accountId);
}
