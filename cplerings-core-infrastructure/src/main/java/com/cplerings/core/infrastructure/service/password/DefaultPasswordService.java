package com.cplerings.core.infrastructure.service.password;

import com.cplerings.core.application.shared.service.password.PasswordService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultPasswordService implements PasswordService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean passwordMatchesEncrypted(String password, String encryptedPassword) {
        return passwordEncoder.matches(password, encryptedPassword);
    }
}
