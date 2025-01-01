package com.cplerings.core.application.dashboard.datasource.data;

import java.util.List;

import com.cplerings.core.domain.payment.Payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Payments {

    private List<Payment> payments;
    private Long count;
    private Integer page;
    private Integer pageSize;
}
