package com.cplerings.core.application.account.output;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public final class AccountOutput {

    private Long id;
    private String email;
    private String username;
    private String phone;
    private String avatar;
    private Boolean hasSpouse;
}
