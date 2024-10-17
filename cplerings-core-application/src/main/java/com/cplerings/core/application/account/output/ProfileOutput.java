package com.cplerings.core.application.account.output;

import lombok.Builder;

@Builder
public record ProfileOutput(Long id, String email, String username, String phone, String avatar) {

}
