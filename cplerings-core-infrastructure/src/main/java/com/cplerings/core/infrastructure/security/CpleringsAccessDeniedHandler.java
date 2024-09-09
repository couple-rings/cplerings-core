package com.cplerings.core.infrastructure.security;

import com.cplerings.core.application.authentication.error.AuthenticationErrorCode;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class CpleringsAccessDeniedHandler implements AccessDeniedHandler {

    private final SecurityHelper securityHelper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        final ErrorCodes errorCodes = ErrorCodes.create(Collections.singletonList(AuthenticationErrorCode.UNAUTHORIZED));
        securityHelper.writeErrorResponse(errorCodes, response, HttpServletResponse.SC_FORBIDDEN);
    }
}
