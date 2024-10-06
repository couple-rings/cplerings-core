package com.cplerings.core.application.account.input;

import lombok.Builder;

@Builder
public record RegisterCustomerInput(String email, String password, String username) {

}
