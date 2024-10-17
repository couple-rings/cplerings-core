package com.cplerings.core.infrastructure.service.security;

import com.cplerings.core.application.shared.service.security.CurrentUser;
import com.cplerings.core.application.shared.service.security.SecurityService;

import lombok.RequiredArgsConstructor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    @Override
    public CurrentUser getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return new CurrentUser(StringUtils.EMPTY, false);
        }
        if (authentication.getPrincipal() instanceof String principal) {
            return new CurrentUser(principal, true);
        }
        return new CurrentUser(StringUtils.EMPTY, false);
    }
}
