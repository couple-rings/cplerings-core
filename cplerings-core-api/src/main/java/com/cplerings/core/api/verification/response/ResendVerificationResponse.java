package com.cplerings.core.api.verification.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.api.verification.data.ResendVerification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResendVerificationResponse extends AbstractDataResponse<ResendVerification> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ResendVerificationResponse, ResendVerification> {

        @Override
        protected ResendVerificationResponse getResponseInstance() {
            return new ResendVerificationResponse();
        }
    }
}
