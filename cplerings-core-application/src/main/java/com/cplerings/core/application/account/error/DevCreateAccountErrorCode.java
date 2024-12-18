package com.cplerings.core.application.account.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum DevCreateAccountErrorCode implements ErrorCode {

    EMAIL_REQUIRED("002", "devCreateAccount.error.emailRequired", Type.VALIDATION),
    PASSWORD_REQUIRED("003", "devCreateAccount.error.passwordRequired", Type.VALIDATION),
    USERNAME_REQUIRED("004", "devCreateAccount.error.usernameRequired", Type.VALIDATION),
    ROLE_REQUIRED("005", "devCreateAccount.error.roleRequired", Type.VALIDATION),
    BRANCH_ID_REQUIRED("006", "devCreateAccount.error.branchIdRequired", Type.VALIDATION),
    INVALID_BRANCH_ID("007", "devCreateAccount.error.invalidBranchId", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
