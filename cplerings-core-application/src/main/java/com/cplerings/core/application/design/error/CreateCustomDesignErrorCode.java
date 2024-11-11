package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CreateCustomDesignErrorCode implements ErrorCode {

    DIAMOND_SPEC_IDS_REQUIRED("002", "createCustomDesign.error.diamondSpecRequired", ErrorCode.Type.VALIDATION),
    METAL_SPEC_IDS_REQUIRED("003", "createCustomDesign.error.metalSpecRequired", ErrorCode.Type.VALIDATION),
    DESIGN_VERSION_HAS_NOT_BEEN_ACCEPTED("004", "createCustomDesign.error.designVersionHasNotBeenAccepted", Type.BUSINESS),
    INVALID_DOCUMENT("005", "createCustomDesign.error.invalidDocument", ErrorCode.Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
