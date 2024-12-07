package com.cplerings.core.application.order.datasource;

import java.util.Optional;

import com.cplerings.core.domain.order.CustomOrder;
import com.cplerings.core.domain.order.CustomOrderHistory;
import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderHistory;

public interface CompleteOrderDataSource {

    Optional<StandardOrder> getStandardOrderById(Long id);

    Optional<CustomOrder> getCustomOrderById(Long id);

    StandardOrder save(StandardOrder order);

    CustomOrder save(CustomOrder customOrder);

    StandardOrderHistory save(StandardOrderHistory history);

    CustomOrderHistory save(CustomOrderHistory history);
}
