package com.cplerings.core.api.design.response;

import com.cplerings.core.api.shared.AbstractDataResponse;
import com.cplerings.core.application.shared.entity.design.ADesign;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewDesignResponse extends AbstractDataResponse<ADesign> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponseBuilder<Builder, ViewDesignResponse, ADesign> {

        @Override
        protected ViewDesignResponse getResponseInstance() {
            return new ViewDesignResponse();
        }
    }
}
