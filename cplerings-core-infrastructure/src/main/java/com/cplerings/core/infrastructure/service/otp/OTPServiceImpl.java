package com.cplerings.core.infrastructure.service.otp;

import com.cplerings.core.application.shared.service.otp.OTPService;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class OTPServiceImpl implements OTPService {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final int MAX_OTP_VALUE = 1000000;
    private static final String OTP_CODE_FORMAT = "%06d";

    @Override
    public String generate() {
        return String.format(OTP_CODE_FORMAT, SECURE_RANDOM.nextInt(0, MAX_OTP_VALUE));
    }
}
