package com.cplerings.core.application.design.input;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record CreateCustomDesignInput(Long designVersionId, Long customerId, Long spouseId, BigDecimal metalWeight, String blueprint, Integer sideDiamondAmount) {
}
