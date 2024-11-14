package com.cplerings.core.application.transport.datasource;

import com.cplerings.core.application.transport.datasource.result.TransportationOrders;
import com.cplerings.core.application.transport.input.ViewTransportationOrdersInput;

public interface ViewTransportationOrdersDataSource {

    TransportationOrders getTransportationOrders(ViewTransportationOrdersInput input);
}
