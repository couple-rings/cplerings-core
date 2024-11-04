package com.cplerings.core.infrastructure.security;

import com.cplerings.core.application.authentication.AuthenticateUserUseCase;
import com.cplerings.core.application.authentication.input.JWTInput;
import com.cplerings.core.application.authentication.output.AuthenticatedAccountOutput;
import com.cplerings.core.application.shared.entity.account.ARole;
import com.cplerings.core.application.shared.errorcode.ErrorCodes;
import com.cplerings.core.common.either.Either;
import com.cplerings.core.common.security.RoleConstant;
import com.cplerings.core.infrastructure.security.mapper.SecurityMapper;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public final class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHENTICATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private final AuthenticateUserUseCase authenticateUserUseCase;
    private final SecurityHelper securityHelper;
    private final SecurityMapper securityMapper;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader(AUTHENTICATION_HEADER);
        if (StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith(BEARER_PREFIX)) {
            final String token = authorizationHeader.substring(BEARER_PREFIX.length());
            final Either<AuthenticatedAccountOutput, ErrorCodes> authenticationEither = authenticateUserUseCase.execute(JWTInput.builder()
                    .token(token)
                    .build());
            if (authenticationEither.isLeft()) {
                authenticateUserWithToken(authenticationEither.getLeft());
            } else {
                securityHelper.writeErrorResponse(authenticationEither.getRight(), response, HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateUserWithToken(AuthenticatedAccountOutput account) {
        final UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(securityMapper.toCurrentUser(account, true),
                null, mapAccountRole(account.getRole()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Collection<? extends GrantedAuthority> mapAccountRole(ARole role) {
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
