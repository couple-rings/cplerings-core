package com.cplerings.core.api.account.request;

import lombok.Builder;

@Builder
public record RegisterCustomerRequest(String email, String password, String username) {

}
