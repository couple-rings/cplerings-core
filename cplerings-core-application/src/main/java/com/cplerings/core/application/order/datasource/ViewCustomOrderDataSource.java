package com.cplerings.core.application.order.datasource;

import java.util.Optional;

import com.cplerings.core.domain.order.CustomOrder;

public interface ViewCustomOrderDataSource {

    Optional<CustomOrder> getCustomOrder(Long customOrderId);
}
