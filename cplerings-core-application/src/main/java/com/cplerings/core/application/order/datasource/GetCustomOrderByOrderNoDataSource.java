package com.cplerings.core.application.order.datasource;

import java.util.Optional;

import com.cplerings.core.domain.order.CustomOrder;

public interface GetCustomOrderByOrderNoDataSource {

    Optional<CustomOrder> getCustomOrderByOrderNo(String orderNo);
}
