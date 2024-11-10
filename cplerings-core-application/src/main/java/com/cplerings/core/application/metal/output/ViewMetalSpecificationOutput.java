package com.cplerings.core.application.metal.output;

import com.cplerings.core.application.shared.entity.design.AMetalSpecification;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewMetalSpecificationOutput extends AbstractPaginatedOutput<AMetalSpecification> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewMetalSpecificationOutput, AMetalSpecification> {

        @Override
        protected ViewMetalSpecificationOutput getOutputInstance() {
            return new ViewMetalSpecificationOutput();
        }
    }

}
