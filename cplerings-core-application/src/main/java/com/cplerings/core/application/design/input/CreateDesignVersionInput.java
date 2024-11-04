package com.cplerings.core.application.design.input;

import lombok.Builder;

@Builder
public record CreateDesignVersionInput(Long designId, Long customerId, Integer versionNumber, String previewImage,
                                       String designFile) {
}
