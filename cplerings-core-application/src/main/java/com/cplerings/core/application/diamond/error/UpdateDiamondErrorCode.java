package com.cplerings.core.application.diamond.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UpdateDiamondErrorCode implements ErrorCode {

    DIAMOND_ID_REQUIRED("002", "updateDiamond.error.diamondIdRequired", Type.VALIDATION),
    DIAMOND_ID_WRONG_INTEGER("003", "updateDiamond.error.diamondIdWrongInteger", Type.VALIDATION),
    DIAMOND_NOT_FOUND("004", "updateDiamond.error.diamondNotFound", Type.BUSINESS),
    DIAMOND_SPEC_NOT_FOUND("005", "updateDiamond.error.diamondSpecNotFound", Type.BUSINESS),
    FOUND_DIAMOND_WITH_THIS_GIA_NUMBER("006", "updateDiamond.error.foundAnotherDiamond", Type.BUSINESS),
    DOCUMENT_NOT_FOUND("007", "updateDiamond.error.documentNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
