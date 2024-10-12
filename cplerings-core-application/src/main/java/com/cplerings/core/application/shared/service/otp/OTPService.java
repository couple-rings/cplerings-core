package com.cplerings.core.application.shared.service.otp;

import com.cplerings.core.application.shared.service.CpleringsService;

@CpleringsService(
        code = "SV-005",
        description = "Service for generating OTP code"
)
public interface OTPService {

    String generate();
}
