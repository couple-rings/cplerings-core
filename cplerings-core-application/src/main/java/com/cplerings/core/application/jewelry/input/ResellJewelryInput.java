package com.cplerings.core.application.jewelry.input;

import com.cplerings.core.application.shared.entity.order.APaymentMethod;

import lombok.Builder;

@Builder
public record ResellJewelryInput(Long jewelryId, Long customerId, Long proofImageId, APaymentMethod paymentMethod,
                                 String note) {

}
