package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.DesignSessionPayment;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateDesignSessionResponse extends AbstractDataResponse<DesignSessionPayment> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, CreateDesignSessionResponse, DesignSessionPayment> {

        @Override
        protected CreateDesignSessionResponse getResponseInstance() {
            return new CreateDesignSessionResponse();
        }
    }
}
