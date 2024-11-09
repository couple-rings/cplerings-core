package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.CustomRequestsData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ViewCustomRequestsResponse extends AbstractPaginatedResponse<CustomRequestsData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewCustomRequestsResponse, CustomRequestsData> {

        @Override
        protected ViewCustomRequestsResponse getResponseInstance() {
            return new ViewCustomRequestsResponse();
        }
    }
}
