package com.cplerings.core.application.design.output;


public record CreateDesignVersionOutput(Long id, String image, String file, int versionNumber, boolean isAccepted, boolean isOld) {
}

