package com.cplerings.core.application.transport.datasource;

import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderHistory;
import com.cplerings.core.domain.order.TransportOrderHistory;
import com.cplerings.core.domain.order.TransportationOrder;

public interface AssignTransportOrderDataSource {

    Optional<Account> getTransporterById(Long transporterId);

    Optional<TransportationOrder> getTransportationOrderById(Long transportationOrderId);

    TransportationOrder save(TransportationOrder transportationOrder);

    CustomOrder save(CustomOrder customOrder);

    CustomOrderHistory save(CustomOrderHistory customOrderHistory);

    TransportOrderHistory save(TransportOrderHistory transportOrderHistory);

    StandardOrder save(StandardOrder standardOrder);

    StandardOrderHistory save(StandardOrderHistory standardOrderHistory);
}
