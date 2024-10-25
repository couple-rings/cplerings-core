package com.cplerings.core.application.design.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DesignErrorCode implements ErrorCode {

    INVALID_METAL_SPECIFICATION_ID("002", "design.error.invalidMetalSpecId", ErrorCode.Type.VALIDATION),
    INVALID_COLLECTION_ID("003", "design.error.invalidCollectionId", ErrorCode.Type.VALIDATION),
    INVALID_PAGE("004", "design.error.invalidPage", ErrorCode.Type.VALIDATION),
    INVALID_PAGE_SIZE("005", "design.error.invalidPageSize", ErrorCode.Type.VALIDATION),
    MIN_PRICE_LARGER_EQUAL_MAX_PRICE("006", "design.error.minPriceLargerOrEqualMaxPrice", ErrorCode.Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
