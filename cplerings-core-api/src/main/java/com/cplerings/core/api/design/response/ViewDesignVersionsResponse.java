package com.cplerings.core.api.design.response;

import com.cplerings.core.api.shared.AbstractPaginatedResponse;

public class ViewDesignVersionsResponse extends AbstractPaginatedResponse<> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewDesignVersionsResponse, > {

        @Override
        protected ViewDesignVersionsResponse getResponseInstance() {
            return new ViewDesignVersionsResponse();
        }
    }
}
