package com.cplerings.core.application.shared.password;

public interface PasswordService {

    String encryptPassword(String password);

    boolean passwordMatchesEncrypted(String password, String encryptedPassword);
}
