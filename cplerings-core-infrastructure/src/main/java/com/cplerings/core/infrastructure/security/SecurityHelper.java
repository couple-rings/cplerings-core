package com.cplerings.core.infrastructure.security;

import com.cplerings.core.api.shared.ErrorCodesResponse;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.domain.Auditor;
import com.cplerings.core.infrastructure.annotation.Helper;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@Helper
@RequiredArgsConstructor
public class SecurityHelper {

    private static final String RESPONSE_ENCODING = "UTF-8";
    private static final String RESPONSE_CONTENT_TYPE = "application/json";

    private final ObjectMapper objectMapper;

    public void writeErrorResponse(ErrorCodes errorCodes, HttpServletResponse response, int status) throws IOException {
        final ErrorCodesResponse errorResponse = ErrorCodesResponse.create(errorCodes);
        response.setStatus(status);
        response.setCharacterEncoding(RESPONSE_ENCODING);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }

    public Optional<Auditor> getCurrentAuditor() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }
        final Object principal = authentication.getPrincipal();
        if (principal instanceof String email) {
            return Optional.of(() -> email);
        } else {
            return Optional.empty();
        }
    }
}
