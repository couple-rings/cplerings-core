package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.DesignVersionsData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewDesignVersionsResponse extends AbstractPaginatedResponse<DesignVersionsData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewDesignVersionsResponse, DesignVersionsData> {

        @Override
        protected ViewDesignVersionsResponse getResponseInstance() {
            return new ViewDesignVersionsResponse();
        }
    }
}
