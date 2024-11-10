package com.cplerings.core.application.shared.service.verification.error;

import com.cplerings.core.application.shared.errorcode.ErrorCode;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum AccountVerificationServiceErrorCode implements ErrorCode {

    ACCOUNT_NOT_IN_VERIFYING_STATUS("SV-004-002", "accountVerificationService.error.accountNotInVerifyingStatus", Type.BUSINESS),
    ACCOUNT_VERIFICATION_CODE_IS_EXPIRED("SV-004-003", "accountVerificationService.error.accountVerificationCodeIsExpired", Type.BUSINESS),
    ACCOUNT_VERIFICATION_CODE_IS_USED("SV-004-004", "accountVerificationService.error.accountVerificationCodeIsUsed", Type.BUSINESS),
    INVALID_VERIFICATION_CODE("SV-004-005", "accountVerificationService.error.invalidVerificationCode", Type.BUSINESS),
    ACCOUNT_VERIFICATION_CODE_IS_INACTIVE("SV-004-006", "accountVerificationService.error.accountVerificationCodeIsInactive", Type.BUSINESS),
    ;

    private final String code;
    private final String descriptionLocale;
    private final Type type;
}
