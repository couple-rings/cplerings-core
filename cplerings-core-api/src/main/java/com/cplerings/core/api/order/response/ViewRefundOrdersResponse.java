package com.cplerings.core.api.order.response;

import com.cplerings.core.api.order.data.RefundsData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewRefundOrdersResponse extends AbstractPaginatedResponse<RefundsData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewRefundOrdersResponse, RefundsData> {

        @Override
        protected ViewRefundOrdersResponse getResponseInstance() {
            return new ViewRefundOrdersResponse();
        }
    }
}
