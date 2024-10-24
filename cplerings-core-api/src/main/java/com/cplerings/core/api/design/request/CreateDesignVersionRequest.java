package com.cplerings.core.api.design.request;

public record CreateDesignVersionRequest(int versionNumber, String previewImage, String designFile) {
}
