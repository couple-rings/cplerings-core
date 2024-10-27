package com.cplerings.core.application.file.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FileUploadErrorCode implements ErrorCode{

    FILE_INPUT_REQUIRED("002", "file.error.invalidInput", ErrorCode.Type.VALIDATION),
    FAULT_IN_UPLOADING_PHASE("003", "file.error.faultInUploadingPhase", ErrorCode.Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
