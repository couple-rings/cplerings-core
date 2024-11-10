package com.cplerings.core.api.metal.data;

import com.cplerings.core.api.shared.AbstractPaginatedData;
import com.cplerings.core.application.shared.entity.design.AMetalSpecification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MetalSpecification extends AbstractPaginatedData<AMetalSpecification> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedDataBuilder<Builder, MetalSpecification, AMetalSpecification> {

        @Override
        protected MetalSpecification getDataInstance() {
            return new MetalSpecification();
        }
    }
}
