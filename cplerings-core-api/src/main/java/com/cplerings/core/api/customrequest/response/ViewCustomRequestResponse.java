package com.cplerings.core.api.customrequest.response;

import com.cplerings.core.api.customrequest.data.CustomRequest;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCustomRequestResponse extends AbstractDataResponse<CustomRequest> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponse.AbstractDataResponseBuilder<Builder, ViewCustomRequestResponse, CustomRequest> {

        @Override
        protected ViewCustomRequestResponse getResponseInstance() {
            return new ViewCustomRequestResponse();
        }
    }
}
