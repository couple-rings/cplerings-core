package com.cplerings.core.api.design.data;

import lombok.Builder;

@Builder
public record DesignSessionPaymentData(Long paymentId, String paymentLink) {

}
