package com.cplerings.core.application.design.input;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreateCustomDesignInput(Long designVersionId, Long customerId, Long spouseId, BigDecimal metalWeight,
                                      String blueprint, Integer sideDiamondAmount) {

}
