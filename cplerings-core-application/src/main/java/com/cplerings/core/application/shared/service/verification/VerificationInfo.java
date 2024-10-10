package com.cplerings.core.application.shared.service.verification;

import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.AccountVerification;

import lombok.Builder;

@Builder
public record VerificationInfo(Account accountToVerify, AccountVerification verification, String verificationCode) {

}
