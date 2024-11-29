package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.DesignCollectionsData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ViewDesignCollectionsResponse extends AbstractPaginatedResponse<DesignCollectionsData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewDesignCollectionsResponse, DesignCollectionsData> {

        @Override
        protected ViewDesignCollectionsResponse getResponseInstance() {
            return new ViewDesignCollectionsResponse();
        }
    }
}
