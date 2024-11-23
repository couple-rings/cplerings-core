package com.cplerings.core.api.account.request;

import java.util.List;

import lombok.Builder;

@Builder
public record ViewUsersRequest(List<Long> userIds) {
}
