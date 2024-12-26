package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewBranchRevenueData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewBranchRevenueResponse extends AbstractDataResponse<ViewBranchRevenueData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponse.AbstractDataResponseBuilder<Builder, ViewBranchRevenueResponse, ViewBranchRevenueData> {

        @Override
        protected ViewBranchRevenueResponse getResponseInstance() {
            return new ViewBranchRevenueResponse();
        }
    }
}
