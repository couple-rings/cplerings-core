package com.cplerings.core.application.account.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ViewUsersErrorCode implements ErrorCode {

    NOT_FOUND_SOME_USERS("002", "viewUsers.error.someOfUsersNotFound", Type.VALIDATION),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
