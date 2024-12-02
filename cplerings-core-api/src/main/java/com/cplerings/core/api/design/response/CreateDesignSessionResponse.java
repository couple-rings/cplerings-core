package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.DesignSessionPaymentData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateDesignSessionResponse extends AbstractDataResponse<DesignSessionPaymentData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, CreateDesignSessionResponse, DesignSessionPaymentData> {

        @Override
        protected CreateDesignSessionResponse getResponseInstance() {
            return new CreateDesignSessionResponse();
        }
    }
}
