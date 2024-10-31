package com.cplerings.core.api.design.request;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record CreateCustomDesignRequest(Long designVersionId, Long customerId, Long spouseId, BigDecimal metalWeight, String blueprint, Integer sideDiamondAmount) {
}
