package com.cplerings.core.application.account.output;

import com.cplerings.core.application.shared.entity.account.AAccount;

import lombok.Builder;

@Builder
public record GetRandomStaffOutput(AAccount staff) {

}
