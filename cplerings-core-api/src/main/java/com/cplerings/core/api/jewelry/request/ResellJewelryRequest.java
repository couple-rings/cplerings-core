package com.cplerings.core.api.jewelry.request;

import com.cplerings.core.application.shared.entity.order.APaymentMethod;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResellJewelryRequest {

    private Long jewelryId;
    private Long customerId;
    private Long proofImageId;
    private APaymentMethod paymentMethod;
    private String note;
}
