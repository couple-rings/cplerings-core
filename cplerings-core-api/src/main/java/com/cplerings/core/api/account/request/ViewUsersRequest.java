package com.cplerings.core.api.account.request;

import lombok.Builder;

import java.util.Set;

@Builder
public record ViewUsersRequest(Set<Long> userIds) {

}
