package com.cplerings.core.infrastructure.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cplerings.core.api.ErrorCodesResponse;
import com.cplerings.core.application.authentication.error.AuthenticationErrorCode;
import com.cplerings.core.application.shared.usecase.ErrorCode;
import com.cplerings.core.application.shared.usecase.ErrorCodes;
import com.cplerings.core.common.security.RoleConstant;
import com.cplerings.core.domain.account.Account;
import com.cplerings.core.domain.account.Role;
import com.cplerings.core.infrastructure.datasource.authentication.JWTDataSource;
import com.cplerings.core.infrastructure.service.jwt.JWTVerificationResult;
import com.cplerings.core.infrastructure.service.jwt.JWTVerificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public final class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHENTICATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final String RESPONSE_ENCODING = "UTF-8";
    private static final String RESPONSE_CONTENT_TYPE = "application/json";

    private final JWTVerificationService jwtVerificationService;
    private final JWTDataSource jwtDataSource;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AUTHENTICATION_HEADER);
        if (StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith(BEARER_PREFIX)) {
            final String token = authorizationHeader.substring(BEARER_PREFIX.length());
            final JWTVerificationResult result = jwtVerificationService.validateToken(token);
            if (result.getStatus() == JWTVerificationResult.Status.VALID) {
                authenticateUserWithToken(result);
            } else {
                writeErrorResponse(result, response);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateUserWithToken(JWTVerificationResult result) {
        final String email = result.getSubject();
        final Optional<Account> authenticatedAccount = jwtDataSource.getAuthenticatedAccount(email);
        if (authenticatedAccount.isPresent()) {
            final Account account = authenticatedAccount.get();
            final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, null, mapAccountRole(account.getRole()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    private Collection<? extends GrantedAuthority> mapAccountRole(Role role) {
        final String roleAuthority = switch (role) {
            case MANAGER -> RoleConstant.ROLE_MANAGER;
            case STAFF -> RoleConstant.ROLE_STAFF;
            case CUSTOMER -> RoleConstant.ROLE_CUSTOMER;
            case JEWELER -> RoleConstant.ROLE_JEWELER;
            case ADMIN -> RoleConstant.ROLE_ADMIN;
            case TRANSPORTER -> RoleConstant.ROLE_TRANSPORTER;
        };
        return Collections.singletonList(new SimpleGrantedAuthority(roleAuthority));
    }

    private void writeErrorResponse(JWTVerificationResult result, HttpServletResponse response) throws IOException {
        final ErrorCode errorCode = switch (result.getStatus()) {
            case INVALID -> AuthenticationErrorCode.INVALID_TOKEN;
            case EXPIRED -> AuthenticationErrorCode.TOKEN_EXPIRED;
            default -> throw new IllegalStateException("Non-invalid case should not be here");
        };
        final ErrorCodesResponse errorResponse = ErrorCodesResponse.create(ErrorCodes.create(Collections.singletonList(errorCode)));
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setCharacterEncoding(RESPONSE_ENCODING);
        response.setContentType(RESPONSE_CONTENT_TYPE);
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
