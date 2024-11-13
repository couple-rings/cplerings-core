package com.cplerings.core.application.account.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ViewTransportersErrorCode implements ErrorCode {

    BRANCH_ID_REQUIRED("002", "viewTransporter.error.branchIdRequired", Type.VALIDATION),
    BRANCH_ID_WRONG_POSITIVE_INTEGER("003", "viewTransporter.error.branchIdWrongPositiveInteger", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
