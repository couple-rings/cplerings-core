package com.cplerings.core.application.order.datasource.data;

import lombok.Builder;

@Builder
public record JewelrySearchInfo(Long branchId, Long metalSpecificationId, Long designId, int count) {

}
