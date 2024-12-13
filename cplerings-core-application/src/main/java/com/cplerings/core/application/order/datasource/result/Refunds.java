package com.cplerings.core.application.order.datasource.result;

import java.util.List;

import com.cplerings.core.domain.refund.Refund;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Refunds {

    private List<Refund> refunds;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
