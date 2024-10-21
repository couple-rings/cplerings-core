package com.cplerings.core.api.dev.response;

import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenerateVNPayPaymentLinkResponse extends AbstractDataResponse<String> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, GenerateVNPayPaymentLinkResponse, String> {

        @Override
        protected GenerateVNPayPaymentLinkResponse getResponseInstance() {
            return new GenerateVNPayPaymentLinkResponse();
        }
    }
}
