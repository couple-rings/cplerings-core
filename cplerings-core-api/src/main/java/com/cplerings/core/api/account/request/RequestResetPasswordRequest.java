package com.cplerings.core.api.account.request;

import lombok.Builder;

@Builder
public record RequestResetPasswordRequest(String email) {

}
