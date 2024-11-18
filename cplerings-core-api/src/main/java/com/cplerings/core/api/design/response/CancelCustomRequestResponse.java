package com.cplerings.core.api.design.response;


import com.cplerings.core.api.design.data.CustomRequest;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CancelCustomRequestResponse extends AbstractDataResponse<CustomRequest> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, CancelCustomRequestResponse, CustomRequest> {

        @Override
        protected CancelCustomRequestResponse getResponseInstance() {
            return new CancelCustomRequestResponse();
        }
    }
}
