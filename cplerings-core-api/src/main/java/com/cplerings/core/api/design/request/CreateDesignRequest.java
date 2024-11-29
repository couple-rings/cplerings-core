package com.cplerings.core.api.design.request;

import java.math.BigDecimal;

import com.cplerings.core.application.shared.entity.design.ADesignCharacteristic;

import lombok.Builder;

@Builder
public record CreateDesignRequest(Long collectionId, Long jewelryCategoryId, BigDecimal metalWeight, String description,
                                  Long blueprintId, ADesignCharacteristic characteristic, Integer size,
                                  Integer sideDiamond, String name) {
}
