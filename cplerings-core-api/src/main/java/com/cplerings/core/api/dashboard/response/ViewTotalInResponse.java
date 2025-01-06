package com.cplerings.core.api.dashboard.response;

import com.cplerings.core.api.dashboard.data.ViewTotalInData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewTotalInResponse extends AbstractDataResponse<ViewTotalInData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ViewTotalInResponse, ViewTotalInData> {

        @Override
        protected ViewTotalInResponse getResponseInstance() {
            return new ViewTotalInResponse();
        }
    }
}
