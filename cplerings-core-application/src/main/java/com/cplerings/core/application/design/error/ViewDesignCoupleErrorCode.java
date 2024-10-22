package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ViewDesignCoupleErrorCode implements ErrorCode {

    INVALID_METAL_SPECIFICATION_ID("002", "design.couple.error.invalidMetalSpecId", ErrorCode.Type.VALIDATION),
    INVALID_COLLECTION_ID("003", "design.couple.error.invalidCollectionId", ErrorCode.Type.VALIDATION);

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}