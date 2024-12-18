package com.cplerings.core.api.dev.request;

import com.cplerings.core.application.shared.entity.account.ARole;

import lombok.Builder;

@Builder
public record DevCreateAccountRequest(String email, String password, String username, ARole role, Long branchId) {

}
