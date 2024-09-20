package com.cplerings.core.api.authentication.response;

import com.cplerings.core.api.authentication.data.AuthenticationToken;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public final class AuthenticationTokenResponse extends AbstractDataResponse<AuthenticationToken> {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Builder extends AbstractDataResponseBuilder<Builder, AuthenticationTokenResponse, AuthenticationToken> {

        @Override
        public AuthenticationTokenResponse build() {
            final AuthenticationTokenResponse response = new AuthenticationTokenResponse();
            response.setType(getType());
            response.setData(getData());
            return response;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
