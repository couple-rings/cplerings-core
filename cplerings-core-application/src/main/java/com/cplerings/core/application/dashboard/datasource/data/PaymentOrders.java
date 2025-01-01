package com.cplerings.core.application.dashboard.datasource.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PaymentOrders {

    private List<PaymentOrder> payments;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
