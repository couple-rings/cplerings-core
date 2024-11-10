package com.cplerings.core.application.design.input;

import lombok.Builder;

@Builder
public record CreateDesignVersionInput(Long designId, Long customerId, String previewImage,
                                       String designFile) {
}
