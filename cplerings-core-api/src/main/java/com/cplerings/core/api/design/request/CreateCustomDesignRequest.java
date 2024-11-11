package com.cplerings.core.api.design.request;

import java.math.BigDecimal;
import java.util.List;

import lombok.Builder;

@Builder
public record CreateCustomDesignRequest(Long designVersionId, Long customerId, Long spouseId, BigDecimal metalWeight,
                                        Long blueprintId, Integer sideDiamondAmount, List<Long> diamondSpecIds,
                                        List<Long> metalSpecIds) {

}
