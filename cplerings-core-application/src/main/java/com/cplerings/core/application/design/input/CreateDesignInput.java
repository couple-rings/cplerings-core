package com.cplerings.core.application.design.input;

import java.math.BigDecimal;
import java.util.List;

import com.cplerings.core.application.design.input.data.MetalSpecInputData;
import com.cplerings.core.application.shared.entity.design.ADesignCharacteristic;

import lombok.Builder;

@Builder
public record CreateDesignInput(Long collectionId, Long jewelryCategoryId, BigDecimal metalWeight, String description,
                                Long blueprintId, ADesignCharacteristic characteristic, Integer size,
                                Integer sideDiamond, String name, List<MetalSpecInputData> metalSpec) {
}
