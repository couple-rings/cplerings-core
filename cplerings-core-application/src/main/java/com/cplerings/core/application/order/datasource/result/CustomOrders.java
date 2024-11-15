package com.cplerings.core.application.order.datasource.result;

import java.util.List;

import com.cplerings.core.domain.metal.MetalSpecification;
import com.cplerings.core.domain.order.CustomOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CustomOrders {

    private List<CustomOrder> customOrders;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
