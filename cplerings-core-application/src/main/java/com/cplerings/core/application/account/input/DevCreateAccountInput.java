package com.cplerings.core.application.account.input;

import com.cplerings.core.application.shared.entity.account.ARole;

import lombok.Builder;

@Builder
public record DevCreateAccountInput(String email, String password, String username, ARole role, Long branchId) {

}
