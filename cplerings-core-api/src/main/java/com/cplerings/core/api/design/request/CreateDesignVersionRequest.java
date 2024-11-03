package com.cplerings.core.api.design.request;

import lombok.Builder;

@Builder
public record CreateDesignVersionRequest(Long designId, Long customerId, Integer versionNumber, String previewImage, String designFile) {
}
