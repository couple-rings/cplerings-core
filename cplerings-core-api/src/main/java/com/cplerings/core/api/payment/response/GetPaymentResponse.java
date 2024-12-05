package com.cplerings.core.api.payment.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.payment.APayment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetPaymentResponse extends AbstractDataResponse<APayment> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, GetPaymentResponse, APayment> {

        @Override
        protected GetPaymentResponse getResponseInstance() {
            return new GetPaymentResponse();
        }
    }
}
