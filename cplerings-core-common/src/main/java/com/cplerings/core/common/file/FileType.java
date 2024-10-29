package com.cplerings.core.common.file;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum FileType {

    JPG("/9j/", "image/jpeg", "jpeg"),
    PNG("iVBORw0KGgo=", "image/png", "png"),
    PDF("JVBER", "application/pdf", "pdf"),;

    private final String magicBytesInBase64;
    private final String contentType;
    private final String extension;

    public static FileType getFileTypeByBase64Extension(String base64Extension) {
        if (StringUtils.isBlank(base64Extension)) {
            return null;
        }
        return switch (StringUtils.trim(base64Extension)) {
            case "data:image/jpeg;base64", "data:image/jpg;base64" -> JPG;
            case "data:image/png;base64" -> PNG;
            case "data:application/pdf;base64" -> PDF;
            default -> null;
        };
    }
}
