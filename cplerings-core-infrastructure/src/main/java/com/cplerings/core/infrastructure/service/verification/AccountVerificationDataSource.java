package com.cplerings.core.infrastructure.service.verification;

import com.cplerings.core.domain.account.AccountVerification;

public interface AccountVerificationDataSource {

    AccountVerification save(AccountVerification accountVerification);
}
