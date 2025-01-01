package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewRefundOrdersWithDateData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ViewRefundOrdersWithDateResponse extends AbstractPaginatedResponse<ViewRefundOrdersWithDateData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewRefundOrdersWithDateResponse, ViewRefundOrdersWithDateData> {

        @Override
        protected ViewRefundOrdersWithDateResponse getResponseInstance() {
            return new ViewRefundOrdersWithDateResponse();
        }
    }
}
