package com.cplerings.core.infrastructure.security;

import java.io.IOException;

import com.cplerings.core.api.ErrorCodesResponse;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.infrastructure.annotation.Helper;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

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
}
