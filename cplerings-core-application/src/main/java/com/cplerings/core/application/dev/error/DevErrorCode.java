package com.cplerings.core.application.dev.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DevErrorCode implements ErrorCode {

    MONEY_REQUIRED("002", "dev.error.moneyRequired", Type.VALIDATION),
    MONEY_MUST_BE_AT_LEAST_10K("003", "dev.error.moneyMustBeAtLeast10K", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
