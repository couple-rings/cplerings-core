package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.CustomRequest;
import com.cplerings.core.api.design.data.CustomRequestData;
import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCustomRequestResponse extends AbstractDataResponse<ACustomRequest> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponse.AbstractDataResponseBuilder<Builder, ViewCustomRequestResponse, ACustomRequest> {

        @Override
        protected ViewCustomRequestResponse getResponseInstance() {
            return new ViewCustomRequestResponse();
        }
    }
}
