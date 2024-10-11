package com.cplerings.core.application.account.input;

import lombok.Builder;

@Builder
public record RequestResetPasswordInput(String email) {

}
