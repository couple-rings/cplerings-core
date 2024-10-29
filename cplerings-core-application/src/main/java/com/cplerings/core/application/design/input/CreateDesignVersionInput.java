package com.cplerings.core.application.design.input;

public record CreateDesignVersionInput(long designId, int versionNumber, String previewImage, String designFile) {
}
