package com.cplerings.core.api.order.response;

import com.cplerings.core.api.order.data.StandardOrderData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetStandardOrderByOrderNoResponse extends AbstractDataResponse<StandardOrderData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, GetStandardOrderByOrderNoResponse, StandardOrderData> {

        @Override
        protected GetStandardOrderByOrderNoResponse getResponseInstance() {
            return new GetStandardOrderByOrderNoResponse();
        }
    }
}
