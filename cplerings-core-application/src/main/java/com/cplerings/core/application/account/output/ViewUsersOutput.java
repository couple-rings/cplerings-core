package com.cplerings.core.application.account.output;

import java.util.List;

import com.cplerings.core.application.shared.entity.account.AAccount;

import lombok.Builder;

@Builder
public record ViewUsersOutput(List<AAccount> users) {
}
