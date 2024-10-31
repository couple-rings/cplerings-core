package com.cplerings.core.application.design.input;

import lombok.Builder;

@Builder
public record CreateDesignVersionInput(long designId, int versionNumber, String previewImage, String designFile) {
}
