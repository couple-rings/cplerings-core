package com.cplerings.core.application.account.input;

import lombok.Builder;

import java.util.Set;

@Builder
public record ViewUsersInput(Set<Long> userIds) {
}
