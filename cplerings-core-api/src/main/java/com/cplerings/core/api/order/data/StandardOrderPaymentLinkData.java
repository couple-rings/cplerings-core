package com.cplerings.core.api.order.data;

import lombok.Builder;

@Builder
public record StandardOrderPaymentLinkData(Long paymentId, String paymentLink) {

}
