package com.cplerings.core.application.design.input;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record CreateCustomDesignInput(long designVersionId, long customerId, long spouseId, BigDecimal metalWeight, String blueprint, int sideDiamondAmount) {
}
