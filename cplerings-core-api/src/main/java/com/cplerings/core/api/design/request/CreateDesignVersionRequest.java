package com.cplerings.core.api.design.request;

import lombok.Builder;

@Builder
public record CreateDesignVersionRequest(long designId, int versionNumber, String previewImage, String designFile) {
}
