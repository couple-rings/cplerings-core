package com.cplerings.core.api.design.request.data;

import lombok.Builder;

@Builder
public record CreateDesignVersionRequestData(Long designId, Long customerId, Long previewImageId,
                                             Long designFileId) {
}
