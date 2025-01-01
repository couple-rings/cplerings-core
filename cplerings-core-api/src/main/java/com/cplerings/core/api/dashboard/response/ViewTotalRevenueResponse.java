package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewTotalRevenueOfBranchData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTotalRevenueResponse extends AbstractDataResponse<ViewTotalRevenueOfBranchData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ViewTotalRevenueResponse, ViewTotalRevenueOfBranchData> {

        @Override
        protected ViewTotalRevenueResponse getResponseInstance() {
            return new ViewTotalRevenueResponse();
        }
    }
}
