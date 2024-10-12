package com.cplerings.core.application.account.input;

import lombok.Builder;

@Builder
public record ResetPasswordInput(String email, String newPassword, String otp) {

}
