package com.cplerings.core.common.file;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum FileMIMEType {

    JPG("/9j/", "image/jpeg", "jpeg"),
    PNG("iVBORw0KGgo=", "image/png", "png"),
    PDF("JVBER", "application/pdf", "pdf"),
    ;

    private final String magicBytesInBase64;
    private final String contentType;
    private final String extension;

    public static FileMIMEType getFileTypeByBase64Extension(String base64Extension) {
        if (StringUtils.isBlank(base64Extension)) {
            return null;
        }
        return switch (StringUtils.trim(base64Extension)) {
            case "data:image/jpeg", "data:image/jpg" -> JPG;
            case "data:image/png" -> PNG;
            case "data:application/pdf" -> PDF;
            default -> null;
        };
    }
}
