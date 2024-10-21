package com.cplerings.core.api.dev.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record GenerateVNPayPaymentLinkRequest(BigDecimal amount) {

}
