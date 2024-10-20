package com.cplerings.core.api.payment.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.api.shared.NoData;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@NoArgsConstructor
public class VNPayPaymentResponse extends AbstractDataResponse<NoData> {

    private String RspCode;
    private String Message;

    public static Builder builder() {
        return new Builder();
    }

    @Getter
    public static final class Builder extends AbstractDataResponseBuilder<Builder, VNPayPaymentResponse, NoData> {

        private String RspCode;
        private String Message;

        public Builder RspCode(String RspCode) {
            this.RspCode = StringUtils.trimToEmpty(RspCode);
            return self();
        }

        public Builder Message(String Message) {
            this.Message = StringUtils.trimToEmpty(Message);
            return self();
        }

        @Override
        public VNPayPaymentResponse build() {
            final VNPayPaymentResponse response = super.build();
            response.setRspCode(RspCode);
            response.setMessage(Message);
            response.setType(Type.INFO);
            return response;
        }

        @Override
        protected VNPayPaymentResponse getResponseInstance() {
            return new VNPayPaymentResponse();
        }
    }
}
