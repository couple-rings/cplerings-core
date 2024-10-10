package com.cplerings.core.api.account.request;

import lombok.Builder;

@Builder
public record VerifyCustomerRequest(String email, String verificationCode) {

}
