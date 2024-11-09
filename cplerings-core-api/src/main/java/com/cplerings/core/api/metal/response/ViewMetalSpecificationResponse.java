package com.cplerings.core.api.metal.response;

import com.cplerings.core.api.metal.data.MetalSpecification;
import com.cplerings.core.api.shared.AbstractPaginatedResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewMetalSpecificationResponse extends AbstractPaginatedResponse<MetalSpecification> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedResponseBuilder<Builder, ViewMetalSpecificationResponse, MetalSpecification> {

        @Override
        protected ViewMetalSpecificationResponse getResponseInstance() {
            return new ViewMetalSpecificationResponse();
        }
    }
}
