package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewTotalExpenditureData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTotalExpenditureResponse extends AbstractDataResponse<ViewTotalExpenditureData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ViewTotalExpenditureResponse, ViewTotalExpenditureData> {

        @Override
        protected ViewTotalExpenditureResponse getResponseInstance() {
            return new ViewTotalExpenditureResponse();
        }
    }
}
