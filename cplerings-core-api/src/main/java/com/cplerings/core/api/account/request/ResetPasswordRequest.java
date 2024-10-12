package com.cplerings.core.api.account.request;

import lombok.Builder;

@Builder
public record ResetPasswordRequest(String email, String newPassword, String otp) {

}
