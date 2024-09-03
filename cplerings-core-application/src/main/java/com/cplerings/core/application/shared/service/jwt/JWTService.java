package com.cplerings.core.application.shared.service.jwt;

public interface JWTService {

    String generateToken(String email);

    String generateRefreshToken(String email);
}
