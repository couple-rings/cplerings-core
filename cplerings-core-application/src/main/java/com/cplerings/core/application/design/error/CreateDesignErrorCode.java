package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CreateDesignErrorCode implements ErrorCode {

    COLLECTION_ID_REQUIRED("002", "createDesign.error.collectionIdRequired", ErrorCode.Type.VALIDATION),
    JEWELRY_CATEGORY_ID_REQUIRED("003", "createDesign.error.jewelryCategoryRequired", ErrorCode.Type.VALIDATION),
    METAL_WEIGHT_REQUIRED("004", "createDesign.error.metalWeightRequired", ErrorCode.Type.VALIDATION),
    DESCRIPTION_REQUIRED("005", "createDesign.error.descriptionRequired", ErrorCode.Type.VALIDATION),
    BLUEPRINT_ID_REQUIRED("006", "createDesign.error.blueprintIdRequired", ErrorCode.Type.VALIDATION),
    CHARACTERISTIC_REQUIRED("007", "createDesign.error.characteristicRequired", ErrorCode.Type.VALIDATION),
    SIZE_REQUIRED("008", "createDesign.error.sizeRequired", ErrorCode.Type.VALIDATION),
    SIZE_DIAMOND_REQUIRED("009", "createDesign.error.sizeDiamondRequired", ErrorCode.Type.VALIDATION),
    COLLECTION_ID_WRONG_INTEGER("010", "createDesign.error.collectionIdWrongInteger", ErrorCode.Type.VALIDATION),
    JEWELRY_CATEGORY_ID_WRONG_INTEGER("011", "createDesign.error.jewelryCategoryIdWrongInteger", ErrorCode.Type.VALIDATION),
    BLUEPRINT_ID_WRONG_INTEGER("012", "createDesign.error.blueprintIdWrongInteger", ErrorCode.Type.VALIDATION),
    SIZE_WRONG_INTEGER("013", "createDesign.error.sizeWrongInteger", ErrorCode.Type.VALIDATION),
    SIZE_DIAMOND_WRONG_INTEGER("014", "createDesign.error.sizeDiamondWrongInteger", ErrorCode.Type.VALIDATION),
    DESIGN_COLLECTION_NOT_FOUND("015", "createDesign.error.designCollectionNotFound", Type.BUSINESS),
    BLUEPRINT_NOT_FOUND("016", "createDesign.error.bluePrintNotFound", Type.BUSINESS),
    JEWELRY_CATEGORY_ID_NOT_FOUND("017", "createDesign.error.jewelryCategoryNotFound", Type.BUSINESS),
    NAME_REQUIRED("018", "createDesign.error.nameRequired", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
