package com.cplerings.core.api.order.request.data;

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
public class ResellCustomOrderRequestData {

    private Long customerId;
    private Long proofImageId;
    private APaymentMethod paymentMethod;
    private String note;
}
