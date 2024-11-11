package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.CustomDesigns;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ViewCustomDesignsResponse extends AbstractPaginatedResponse<CustomDesigns>{

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponse.AbstractPaginatedResponseBuilder<Builder, ViewCustomDesignsResponse, CustomDesigns> {

        @Override
        protected ViewCustomDesignsResponse getResponseInstance() {
            return new ViewCustomDesignsResponse();
        }
    }
}
