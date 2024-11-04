package com.cplerings.core.api.design.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.design.request.ACustomRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCustomRequestResponse extends AbstractDataResponse<ACustomRequest> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, CreateCustomRequestResponse, ACustomRequest> {

        @Override
        protected CreateCustomRequestResponse getResponseInstance() {
            return new CreateCustomRequestResponse();
        }
    }
}
