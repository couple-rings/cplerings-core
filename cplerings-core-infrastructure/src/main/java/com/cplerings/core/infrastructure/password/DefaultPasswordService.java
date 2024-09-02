package com.cplerings.core.infrastructure.password;

import com.cplerings.core.application.shared.password.PasswordService;
import org.springframework.stereotype.Service;

@Service
public class DefaultPasswordService implements PasswordService {

    @Override
    public String encryptPassword(String password) {
        return "";
    }

    @Override
    public boolean passwordMatchesEncrypted(String password, String encryptedPassword) {
        return true;
    }
}
