package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewTotalOrdersOfBranchData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTotalOrdersOfBranchResponse extends AbstractDataResponse<ViewTotalOrdersOfBranchData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ViewTotalOrdersOfBranchResponse, ViewTotalOrdersOfBranchData> {

        @Override
        protected ViewTotalOrdersOfBranchResponse getResponseInstance() {
            return new ViewTotalOrdersOfBranchResponse();
        }
    }
}
