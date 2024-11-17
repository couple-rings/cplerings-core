package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.CreateDesignVersionData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateDesignVersionResponse extends AbstractDataResponse<CreateDesignVersionData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, CreateDesignVersionResponse, CreateDesignVersionData> {

        @Override
        protected CreateDesignVersionResponse getResponseInstance() {
            return new CreateDesignVersionResponse();
        }
    }
}
