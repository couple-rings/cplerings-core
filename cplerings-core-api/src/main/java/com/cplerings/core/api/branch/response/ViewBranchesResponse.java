package com.cplerings.core.api.branch.response;

import com.cplerings.core.api.branch.data.BranchesData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewBranchesResponse extends AbstractPaginatedResponse<BranchesData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewBranchesResponse, BranchesData> {

        @Override
        protected ViewBranchesResponse getResponseInstance() {
            return new ViewBranchesResponse();
        }
    }
}
