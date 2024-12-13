package com.cplerings.core.application.diamond.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DisableDiamondErrorCode implements ErrorCode {

    DIAMOND_NOT_FOUND("002", "disableDiamond.error.diamondNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
