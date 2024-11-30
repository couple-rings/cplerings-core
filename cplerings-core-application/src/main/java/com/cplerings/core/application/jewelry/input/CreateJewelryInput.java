package com.cplerings.core.application.jewelry.input;

import lombok.Builder;

@Builder
public record CreateJewelryInput(Long diamondId, Long metalSpecId, Long designId, Long branchId) {
}