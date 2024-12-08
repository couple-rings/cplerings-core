package com.cplerings.core.api.order.request;

import com.cplerings.core.application.shared.entity.order.ARefundMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundCustomOrderRequest {

    private Long customOrderId;
    private Long staffId;
    private String reason;
    private Long proofImageId;
    private ARefundMethod method;
}
