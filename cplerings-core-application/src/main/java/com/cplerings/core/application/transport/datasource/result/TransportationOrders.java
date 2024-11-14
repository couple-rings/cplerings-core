package com.cplerings.core.application.transport.datasource.result;

import java.util.List;

import com.cplerings.core.domain.order.TransportationOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TransportationOrders {

    private List<TransportationOrder> transportationOrders;
    private long count;
    private int page;
    private int pageSize;
}
