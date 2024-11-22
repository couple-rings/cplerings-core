package com.cplerings.core.application.spouse.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum VerifyResidentIdErrorCode implements ErrorCode {

    CITIZEN_ID_REQUIRED("002", "verifySpouse.error.citizenIdRequired", ErrorCode.Type.VALIDATION),
    SPOUSE_NOT_FOUND_WITH_CITIZEN_ID("003", "verifySpouse.error.citizenIdNotFound", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
