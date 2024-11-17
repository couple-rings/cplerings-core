package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.DesignSessionLeftData;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewDesignSessionsLeftResponse extends AbstractDataResponse<DesignSessionLeftData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractDataResponse.AbstractDataResponseBuilder<Builder, ViewDesignSessionsLeftResponse, DesignSessionLeftData> {

        @Override
        protected ViewDesignSessionsLeftResponse getResponseInstance() {
            return new ViewDesignSessionsLeftResponse();
        }
    }
}
