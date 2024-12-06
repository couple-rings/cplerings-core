package com.cplerings.core.api.order.response;

import com.cplerings.core.api.order.data.StandardOrderData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CancelStandardOrderResponse extends AbstractDataResponse<StandardOrderData>{

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, CancelStandardOrderResponse, StandardOrderData> {

        @Override
        protected CancelStandardOrderResponse getResponseInstance() {
            return new CancelStandardOrderResponse();
        }
    }
}
