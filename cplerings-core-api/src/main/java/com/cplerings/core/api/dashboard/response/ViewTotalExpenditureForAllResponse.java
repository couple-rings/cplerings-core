package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewTotalExpenditureForAllData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTotalExpenditureForAllResponse extends AbstractDataResponse<ViewTotalExpenditureForAllData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ViewTotalExpenditureForAllResponse, ViewTotalExpenditureForAllData> {

        @Override
        protected ViewTotalExpenditureForAllResponse getResponseInstance() {
            return new ViewTotalExpenditureForAllResponse();
        }
    }
}
