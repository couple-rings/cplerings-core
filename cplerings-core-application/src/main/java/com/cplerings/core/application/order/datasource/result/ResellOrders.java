package com.cplerings.core.application.order.datasource.result;

import java.util.List;

import com.cplerings.core.domain.resell.ResellOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ResellOrders {

    private List<ResellOrder> resellOrders;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
