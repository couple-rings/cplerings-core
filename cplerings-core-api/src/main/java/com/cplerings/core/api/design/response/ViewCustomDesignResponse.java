package com.cplerings.core.api.design.response;

import com.cplerings.core.api.design.data.CustomDesign;
import com.cplerings.core.api.design.data.DesignVersion;
import com.cplerings.core.api.shared.AbstractDataResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewCustomDesignResponse extends AbstractDataResponse<CustomDesign> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder
            extends AbstractDataResponseBuilder<Builder, ViewCustomDesignResponse, CustomDesign> {

        @Override
        protected ViewCustomDesignResponse getResponseInstance() {
            return new ViewCustomDesignResponse();
        }
    }
}
