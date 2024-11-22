package com.cplerings.core.api.agreement.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.agreement.AAgreement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignAgreementResponse extends AbstractDataResponse<AAgreement> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, SignAgreementResponse, AAgreement> {

        @Override
        protected SignAgreementResponse getResponseInstance() {
            return new SignAgreementResponse();
        }
    }
}
