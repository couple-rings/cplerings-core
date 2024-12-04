package com.cplerings.core.api.order.response;

import com.cplerings.core.api.order.data.StandardOrdersData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewStandardOrdersResponse extends AbstractPaginatedResponse<StandardOrdersData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewStandardOrdersResponse, StandardOrdersData> {

        @Override
        protected ViewStandardOrdersResponse getResponseInstance() {
            return new ViewStandardOrdersResponse();
        }
    }
}
