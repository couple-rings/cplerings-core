package com.cplerings.core.application.design.input.data;

import lombok.Builder;

@Builder
public record CreateDesignVersionInputData(Long designId, Long customerId, Long previewImageId,
                                           Long designFileId) {
}
