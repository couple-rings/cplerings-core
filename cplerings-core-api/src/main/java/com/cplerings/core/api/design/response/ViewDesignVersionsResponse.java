package com.cplerings.core.api.design.response;

import com.cplerings.core.api.shared.AbstractPaginatedResponse;
import com.cplerings.core.application.design.datasource.result.DesignVersions;

public class ViewDesignVersionsResponse extends AbstractPaginatedResponse<DesignVersions> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewDesignVersionsResponse, DesignVersions> {

        @Override
        protected ViewDesignVersionsResponse getResponseInstance() {
            return new ViewDesignVersionsResponse();
        }
    }
}
