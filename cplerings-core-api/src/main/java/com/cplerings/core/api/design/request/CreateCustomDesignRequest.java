package com.cplerings.core.api.design.request;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record CreateCustomDesignRequest(long designVersionId, long customerId, long spouseId, BigDecimal metalWeight, String blueprint, int sideDiamondAmount) {
}
