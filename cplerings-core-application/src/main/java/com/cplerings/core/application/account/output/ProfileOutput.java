package com.cplerings.core.application.account.output;

import com.cplerings.core.application.shared.entity.account.AAccount;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public final class ProfileOutput {

    private AAccount account;
    private Boolean hasSpouse;
}
