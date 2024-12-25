package com.cplerings.core.application.order.input;

import com.cplerings.core.application.shared.entity.order.APaymentMethod;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResellCustomOrderInput {

    private Long customOrderId;
    private Long customerId;
    private Long proofImageId;
    private APaymentMethod paymentMethod;
    private String note;
}
