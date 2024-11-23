package com.cplerings.core.application.account.input;

import java.util.List;

import lombok.Builder;

@Builder
public record ViewUsersInput(List<Long> userIds) {
}
