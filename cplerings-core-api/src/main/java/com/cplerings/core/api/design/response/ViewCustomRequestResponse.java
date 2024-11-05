package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.CustomRequestData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCustomRequestResponse extends AbstractDataResponse<CustomRequestData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponse.AbstractDataResponseBuilder<Builder, ViewCustomRequestResponse, CustomRequestData> {

        @Override
        protected ViewCustomRequestResponse getResponseInstance() {
            return new ViewCustomRequestResponse();
        }
    }
}