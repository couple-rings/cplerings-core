package com.cplerings.core.application.transport.datasource;

import com.cplerings.core.application.transport.datasource.result.TransportationAddresses;
import com.cplerings.core.application.transport.input.ViewTransportationAddressesInput;

public interface ViewTransportationAddressesDataSource {

    TransportationAddresses getTransportationAddresses(ViewTransportationAddressesInput input);
}
