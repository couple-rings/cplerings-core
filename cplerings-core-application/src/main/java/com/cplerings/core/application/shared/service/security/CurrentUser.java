package com.cplerings.core.application.shared.service.security;

import com.cplerings.core.domain.account.Role;

import lombok.Builder;

@Builder
public record CurrentUser(Long id, String email, Role role, boolean authenticated) {

}
