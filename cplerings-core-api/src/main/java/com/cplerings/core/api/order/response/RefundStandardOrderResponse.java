package com.cplerings.core.api.order.response;

import com.cplerings.core.api.order.data.RefundData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RefundStandardOrderResponse extends AbstractDataResponse<RefundData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, RefundStandardOrderResponse, RefundData> {

        @Override
        protected RefundStandardOrderResponse getResponseInstance() {
            return new RefundStandardOrderResponse();
        }
    }
}
