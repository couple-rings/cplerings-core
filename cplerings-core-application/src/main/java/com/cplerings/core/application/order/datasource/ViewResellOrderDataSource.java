package com.cplerings.core.application.order.datasource;

import java.util.Optional;

import com.cplerings.core.domain.resell.ResellOrder;

public interface ViewResellOrderDataSource {

    Optional<ResellOrder> getResellOrderById(Long orderId);
}
