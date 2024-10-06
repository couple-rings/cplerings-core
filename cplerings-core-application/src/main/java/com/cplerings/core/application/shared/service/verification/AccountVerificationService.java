package com.cplerings.core.application.shared.service.verification;

public interface AccountVerificationService {

    VerificationResult verifyAccount(VerificationInfo verificationInfo);

    VerificationCode generateVerificationCode(VerificationInfo verificationInfo);
}
