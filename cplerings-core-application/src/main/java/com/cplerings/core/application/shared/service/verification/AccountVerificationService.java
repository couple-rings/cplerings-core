package com.cplerings.core.application.shared.service.verification;

import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.application.shared.service.CpleringsService;
import com.cplerings.core.common.either.Either;
import com.cplerings.core.common.either.NoResult;

@CpleringsService(
        code = "SV-004",
        description = "Service for account verification"
)
public interface AccountVerificationService {

    Either<NoResult, ErrorCodes> verifyAccount(VerificationInfo verificationInfo);

    VerificationCode generateVerificationCode(VerificationInfo verificationInfo);
}
