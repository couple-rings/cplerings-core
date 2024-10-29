package com.cplerings.core.application.file.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileErrorCode implements ErrorCode {

    INVALID_FILE_FORMAT("004", "file.error.invalidFileFormat", ErrorCode.Type.VALIDATION),
    INVALID_MAGIC_BYTES("005", "file.error.invalidMagicBytes", ErrorCode.Type.VALIDATION),
    FILE_EXCEED_MAX_ALLOWED_SIZE("006", "file.error.fileExceedMaxAllowedSize", ErrorCode.Type.VALIDATION),
    EMPTY_FILE("007", "file.error.emptyFile", ErrorCode.Type.VALIDATION),
    FILE_UPLOAD_FAILED("008", "file.error.fileUploadFailed", Type.SYSTEM),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
