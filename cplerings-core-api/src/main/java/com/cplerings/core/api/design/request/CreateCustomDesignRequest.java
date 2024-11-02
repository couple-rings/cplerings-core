package com.cplerings.core.api.design.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreateCustomDesignRequest(Long designVersionId, Long customerId, Long spouseId, BigDecimal metalWeight,
                                        String blueprint, Integer sideDiamondAmount) {

}
