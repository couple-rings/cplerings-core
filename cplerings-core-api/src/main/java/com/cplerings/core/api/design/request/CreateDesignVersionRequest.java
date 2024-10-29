package com.cplerings.core.api.design.request;

public record CreateDesignVersionRequest(long designId, int versionNumber, String previewImage, String designFile) {
}
