package com.cplerings.core.application.dashboard.datasource.data;

import java.util.List;

import com.cplerings.core.domain.account.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CombinedOrders {

    private List<CombinedOrder> orders;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
