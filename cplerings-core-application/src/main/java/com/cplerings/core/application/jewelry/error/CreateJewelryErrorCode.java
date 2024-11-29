package com.cplerings.core.application.jewelry.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CreateJewelryErrorCode implements ErrorCode {

    BRANCH_ID_REQUIRED("002", "createJewelry.error.branchIdRequired", Type.VALIDATION),
    DESIGN_ID_REQUIRED("003", "createJewelry.error.designIdRequired", Type.VALIDATION),
    DIAMOND_ID_REQUIRED("004", "createJewelry.error.diamondIdRequired", Type.VALIDATION),
    METAL_SPEC_ID_REQUIRED("005", "createJewelry.error.metalSpecIdRequired", Type.VALIDATION),
    DESIGN_ID_WRONG_INTEGER("006", "createJewelry.error.designIdWrongInteger", Type.VALIDATION),
    BRANCH_ID_WRONG_INTEGER("007", "createJewelry.error.branchIdWrongInteger", Type.VALIDATION),
    DIAMOND_ID_WRONG_INTEGER("008", "createJewelry.error.diamondIdWrongInteger", Type.VALIDATION),
    METAL_SPEC_ID_WRONG_INTEGER("009", "createJewelry.error.metalSpecIdWrongInteger", Type.VALIDATION),
    BRANCH_NOT_FOUND("010", "createJewelry.error.branchNotFound", Type.VALIDATION),
    DESIGN_NOT_FOUND("011", "createJewelry.error.designNotFound", Type.VALIDATION),
    DIAMOND_NOT_FOUND("012", "createJewelry.error.diamondNotFound", Type.VALIDATION),
    METAL_SPEC_NOT_FOUND("013", "createJewelry.error.metalSpecNotFound", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
