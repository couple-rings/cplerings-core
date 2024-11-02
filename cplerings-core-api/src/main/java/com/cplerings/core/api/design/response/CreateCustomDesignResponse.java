package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.CustomDesignData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateCustomDesignResponse extends AbstractDataResponse<CustomDesignData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, CreateCustomDesignResponse, CustomDesignData> {

        @Override
        protected CreateCustomDesignResponse getResponseInstance() {
            return new CreateCustomDesignResponse();
        }
    }
}
