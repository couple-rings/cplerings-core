package com.cplerings.core.application.jewelry.input;

import com.cplerings.core.domain.resell.PaymentMethod;

import lombok.Builder;

@Builder
public record ResellJewelryInput(Long jewelryId, Long customerId, Long proofImageId, PaymentMethod paymentMethod,
                                 String note) {

}
