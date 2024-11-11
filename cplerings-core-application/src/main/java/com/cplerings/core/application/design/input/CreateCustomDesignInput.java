package com.cplerings.core.application.design.input;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

@Builder
public record CreateCustomDesignInput(Long designVersionId, Long customerId, Long spouseId, BigDecimal metalWeight,
                                      Long blueprintId, Integer sideDiamondAmount, List<Long> diamondSpecIds,
                                      List<Long> metalSpecIds) {

}
