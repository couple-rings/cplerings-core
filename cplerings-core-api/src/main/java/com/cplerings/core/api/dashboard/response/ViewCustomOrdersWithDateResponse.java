package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewCustomOrdersWithDateData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ViewCustomOrdersWithDateResponse extends AbstractPaginatedResponse<ViewCustomOrdersWithDateData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewCustomOrdersWithDateResponse, ViewCustomOrdersWithDateData> {

        @Override
        protected ViewCustomOrdersWithDateResponse getResponseInstance() {
            return new ViewCustomOrdersWithDateResponse();
        }
    }
}
