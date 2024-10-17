package com.cplerings.core.application.shared.service.security;

import lombok.Builder;

@Builder
public record CurrentUser(String email, boolean authenticated) {

}
