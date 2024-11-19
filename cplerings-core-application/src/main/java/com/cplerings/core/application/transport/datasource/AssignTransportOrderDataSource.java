package com.cplerings.core.application.transport.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.TransportationOrder;

public interface AssignTransportOrderDataSource {

    Optional<Account> getTransporterById(Long transporterId);

    Optional<TransportationOrder> getTransportationOrderById(Long transportationOrderId);

    TransportationOrder save(TransportationOrder transportationOrder);

    void save(CustomOrder customOrder);
}
