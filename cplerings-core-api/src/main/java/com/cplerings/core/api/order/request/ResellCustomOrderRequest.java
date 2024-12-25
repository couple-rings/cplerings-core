package com.cplerings.core.api.order.request;

import com.cplerings.core.application.shared.entity.order.APaymentMethod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResellCustomOrderRequest {

    private Long customOrderId;
    private Long customerId;
    private Long proofImageId;
    private APaymentMethod paymentMethod;
    private String note;
}
