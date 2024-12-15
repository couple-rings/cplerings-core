package com.cplerings.core.application.account.datasource;

import com.cplerings.core.application.account.datasource.result.Customers;
import com.cplerings.core.application.account.input.ViewCustomersInput;

public interface ViewCustomersDataSource {

    Customers getCustomers(ViewCustomersInput input);
}
