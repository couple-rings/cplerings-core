package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewTotalInForAllData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTotalInForAllResponse extends AbstractDataResponse<ViewTotalInForAllData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ViewTotalInForAllResponse, ViewTotalInForAllData> {

        @Override
        protected ViewTotalInForAllResponse getResponseInstance() {
            return new ViewTotalInForAllResponse();
        }
    }
}
