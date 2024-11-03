package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.DesignVersion;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateDesignVersionStatusResponse extends AbstractDataResponse<DesignVersion> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, UpdateDesignVersionStatusResponse, DesignVersion> {

        @Override
        protected UpdateDesignVersionStatusResponse getResponseInstance() {
            return new UpdateDesignVersionStatusResponse();
        }
    }
}
