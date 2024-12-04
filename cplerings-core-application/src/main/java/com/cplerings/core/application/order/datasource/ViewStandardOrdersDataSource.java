package com.cplerings.core.application.order.datasource;

import com.cplerings.core.application.order.datasource.result.StandardOrders;
import com.cplerings.core.application.order.input.ViewStandardOrdersInput;

public interface ViewStandardOrdersDataSource {

    StandardOrders getStandardOrders(ViewStandardOrdersInput input);
}
