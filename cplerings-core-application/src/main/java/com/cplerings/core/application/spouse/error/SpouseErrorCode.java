package com.cplerings.core.application.spouse.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SpouseErrorCode implements ErrorCode{

    WILL_BE_REPLACED("002", "spouse.error.spouseHasBeenCreated",ErrorCode.Type.BUSINESS),
    PRIMARY_SPOUSE_REQUIRED("002", "spouse.error.primarySpouseRequired", Type.VALIDATION),
    SECONDARY_SPOUSE_REQUIRED("003", "spouse.error.secondarySpouseRequired",ErrorCode.Type.VALIDATION),
    SPOUSE_HAS_BEEN_CREATED("004", "spouse.error.spouseHasBeenCreated",ErrorCode.Type.BUSINESS);

    private final String code;
    private final String descriptionLocale;
    private final ErrorCode.Type type;
}
