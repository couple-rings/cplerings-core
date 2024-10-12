package com.cplerings.core.application.account.output;

import lombok.Builder;

@Builder
public record RequestResetPasswordOutput(String email) {

}
