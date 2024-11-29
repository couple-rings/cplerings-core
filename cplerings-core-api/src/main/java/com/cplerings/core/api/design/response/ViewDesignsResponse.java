package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.DesignsData;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ViewDesignsResponse extends AbstractPaginatedResponse<DesignsData> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewDesignsResponse, DesignsData> {

        @Override
        protected ViewDesignsResponse getResponseInstance() {
            return new ViewDesignsResponse();
        }
    }
}
