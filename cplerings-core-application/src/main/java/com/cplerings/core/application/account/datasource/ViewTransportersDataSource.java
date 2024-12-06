package com.cplerings.core.application.account.datasource;

import com.cplerings.core.application.account.datasource.result.Transporters;
import com.cplerings.core.application.account.input.ViewTransportersInput;
import com.cplerings.core.application.shared.entity.account.ATransporter;

public interface ViewTransportersDataSource {

    Transporters getTransporters(ViewTransportersInput input);

    Long calculateNoOfHandleTransportationOrders(ATransporter transporter);
}
