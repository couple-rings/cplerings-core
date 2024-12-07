package com.cplerings.core.application.order.datasource;

import java.util.Optional;

import com.cplerings.core.domain.order.StandardOrder;

public interface GetStandardOrderByOrderNoDataSource {

    Optional<StandardOrder> getStandardOrderByOrderNo(String orderNo);
}
