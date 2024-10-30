package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.CustomDesign;
import com.cplerings.core.api.design.data.RemainingDesignSessionData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCustomDesignResponse extends AbstractDataResponse<CustomDesign> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, CreateCustomDesignResponse, CustomDesign> {

        @Override
        protected CreateCustomDesignResponse getResponseInstance() {
            return new CreateCustomDesignResponse();
        }
    }
}
