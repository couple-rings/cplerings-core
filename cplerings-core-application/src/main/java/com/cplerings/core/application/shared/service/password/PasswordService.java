package com.cplerings.core.application.shared.service.password;

public interface PasswordService {

    String encryptPassword(String password);

    boolean passwordMatchesEncrypted(String password, String encryptedPassword);
}
