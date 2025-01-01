package com.cplerings.core.application.dashboard.datasource;

import com.cplerings.core.application.dashboard.datasource.data.Payments;
import com.cplerings.core.application.dashboard.input.ViewPaymentWithDateInput;
import com.cplerings.core.domain.account.Account;

public interface ViewPaymentWithDateDataSource {

    Payments getPayments(ViewPaymentWithDateInput input, Long branchId);

    Account getAccountById(Long id);
}
