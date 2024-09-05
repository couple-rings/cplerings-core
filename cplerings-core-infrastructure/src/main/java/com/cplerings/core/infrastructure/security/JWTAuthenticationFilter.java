package com.cplerings.core.infrastructure.security;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cplerings.core.application.authentication.AuthenticateUserJWTUseCase;
import com.cplerings.core.application.authentication.input.JWTInput;
import com.cplerings.core.application.authentication.output.AccountOutput;
import com.cplerings.core.application.authentication.output.RoleOutput;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.common.pair.Pair;
import com.cplerings.core.common.security.RoleConstant;

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

    private final AuthenticateUserJWTUseCase authenticateUserJWTUseCase;
    private final SecurityHelper securityHelper;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AUTHENTICATION_HEADER);
        if (StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith(BEARER_PREFIX)) {
            final String token = authorizationHeader.substring(BEARER_PREFIX.length());
            final Pair<AccountOutput, ErrorCodes> authenticationPair = authenticateUserJWTUseCase.authenticate(JWTInput.builder()
                    .token(token)
                    .build());
            if (authenticationPair.isLeft()) {
                authenticateUserWithToken(authenticationPair.getLeft());
            } else {
                securityHelper.writeErrorResponse(authenticationPair.getRight(), response, HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateUserWithToken(AccountOutput account) {
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(account.getEmail(),
                null, mapAccountRole(account.getRole()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Collection<? extends GrantedAuthority> mapAccountRole(RoleOutput role) {
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
}
