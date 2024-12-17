package com.cplerings.core.application.jewelry.input;

import lombok.Builder;

@Builder
public record UpdateJewelryInput(Long jewelryId, Long metalSpecId, Long designId) {
}
