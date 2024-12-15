package com.cplerings.core.application.jewelry.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum GetJewelryByProductNoErrorCode implements ErrorCode {

    JEWELRY_PRODUCT_NO_REQUIRED("002", "getJewelryByProductNo.error.productNoNotFound", Type.VALIDATION),
    JEWELRY_NOT_FOUND("003", "getJewelryByProductNo.error.jewelryNotFound", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
