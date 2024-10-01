package com.cplerings.core.api.authentication.data;

import lombok.Builder;

@Builder
public record AuthenticationToken(String token, String refreshToken) {

}
