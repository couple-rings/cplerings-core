package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.CustomRequest;
import com.cplerings.core.api.design.data.CustomRequestData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetermineCustomRequestResponse extends AbstractDataResponse<CustomRequest> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, DetermineCustomRequestResponse, CustomRequest> {

        @Override
        protected DetermineCustomRequestResponse getResponseInstance() {
            return new DetermineCustomRequestResponse();
        }
    }
}
