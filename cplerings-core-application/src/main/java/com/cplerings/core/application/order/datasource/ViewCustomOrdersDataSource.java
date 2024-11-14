package com.cplerings.core.application.order.datasource;

import com.cplerings.core.application.order.datasource.result.CustomOrders;
import com.cplerings.core.application.order.input.ViewCustomOrdersInput;

public interface ViewCustomOrdersDataSource {

    CustomOrders getCustomOrders(ViewCustomOrdersInput input);
}
