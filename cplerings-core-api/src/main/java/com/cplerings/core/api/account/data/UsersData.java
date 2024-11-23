package com.cplerings.core.api.account.data;

import java.util.List;

import com.cplerings.core.application.shared.entity.account.AAccount;

import lombok.Builder;

@Builder
public record UsersData(List<AAccount> users) {
}
