package com.cplerings.core.application.account.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public final class ProfileOutput {

    private Long id;
    private String email;
    private String username;
    private String phone;
    private String avatar;
    private boolean hasSpouse;
}
