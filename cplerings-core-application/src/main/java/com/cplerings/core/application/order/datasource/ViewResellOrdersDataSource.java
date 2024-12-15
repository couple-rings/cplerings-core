package com.cplerings.core.application.order.datasource;

import com.cplerings.core.application.order.datasource.result.ResellOrders;
import com.cplerings.core.application.order.input.ViewResellOrdersInput;

public interface ViewResellOrdersDataSource {

    ResellOrders getResellOrders(ViewResellOrdersInput input);
}
