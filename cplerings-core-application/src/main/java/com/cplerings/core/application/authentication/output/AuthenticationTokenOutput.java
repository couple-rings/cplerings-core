package com.cplerings.core.application.authentication.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class AuthenticationTokenOutput {

    private String token;
    private String refreshToken;
}
