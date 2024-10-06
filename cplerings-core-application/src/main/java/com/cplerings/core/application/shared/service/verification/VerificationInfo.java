package com.cplerings.core.application.shared.service.verification;

import com.cplerings.core.domain.account.Account;

import lombok.Builder;

@Builder
public record VerificationInfo(Account accountToVerify, String verificationCode) {

}
