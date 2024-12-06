package com.cplerings.core.application.order.datasource;

import java.util.Optional;

import com.cplerings.core.domain.order.StandardOrder;
import com.cplerings.core.domain.order.StandardOrderHistory;

public interface CancelStandardOrderDataSource {

    Optional<StandardOrder> getStandardOrderById(Long id);

    StandardOrder save(StandardOrder standardOrder);

    StandardOrderHistory save(StandardOrderHistory standardOrderHistory);
}
