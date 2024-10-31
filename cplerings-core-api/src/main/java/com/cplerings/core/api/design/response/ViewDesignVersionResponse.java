package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.DesignVersion;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewDesignVersionResponse extends AbstractDataResponse<DesignVersion> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponse.AbstractDataResponseBuilder<Builder, ViewDesignVersionResponse, DesignVersion> {

        @Override
        protected ViewDesignVersionResponse getResponseInstance() {
            return new ViewDesignVersionResponse();
        }
    }
}
