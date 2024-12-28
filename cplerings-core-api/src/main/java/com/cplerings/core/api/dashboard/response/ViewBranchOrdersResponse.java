package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewBranchOrdersData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewBranchOrdersResponse extends AbstractDataResponse<ViewBranchOrdersData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ViewBranchOrdersResponse, ViewBranchOrdersData> {

        @Override
        protected ViewBranchOrdersResponse getResponseInstance() {
            return new ViewBranchOrdersResponse();
        }
    }
}
