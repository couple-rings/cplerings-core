package com.cplerings.core.application.dev.input;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record GenerateVNPayPaymentLinkInput(BigDecimal amount) {

}
