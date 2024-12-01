package com.cplerings.core.api.order.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.order.AStandardOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateStandardOrderResponse extends AbstractDataResponse<AStandardOrder> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, CreateStandardOrderResponse, AStandardOrder> {

        @Override
        protected CreateStandardOrderResponse getResponseInstance() {
            return new CreateStandardOrderResponse();
        }
    }
}
