package com.cplerings.core.application.order.datasource;

import java.util.List;
import java.util.Optional;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.address.TransportationAddress;
import com.cplerings.core.domain.jewelry.Jewelry;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderHistory;
import com.cplerings.core.domain.order.StandardOrderItem;
import com.cplerings.core.domain.order.TransportOrderHistory;
import com.cplerings.core.domain.order.TransportationOrder;

public interface CreateStandardOrderDataSource {

    Optional<Account> getCustomerById(Long id);

    Optional<Jewelry> getJewelryById(Long id);

    StandardOrder save(StandardOrder order);

    StandardOrderHistory save(StandardOrderHistory history);

    List<Jewelry> saveJewelries(List<Jewelry> jewelries);

    List<StandardOrderItem> saveItems(List<StandardOrderItem> items);

    Optional<TransportationAddress> getAddressById(Long id);

    TransportationOrder save(TransportationOrder order);

    TransportOrderHistory save(TransportOrderHistory history);
}
