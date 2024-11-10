package com.cplerings.core.application.diamond.output;

import com.cplerings.core.application.shared.entity.design.ADiamondSpecification;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewDiamondSpecificationOutput extends AbstractPaginatedOutput<ADiamondSpecification> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewDiamondSpecificationOutput, ADiamondSpecification> {

        @Override
        protected ViewDiamondSpecificationOutput getOutputInstance() {
            return new ViewDiamondSpecificationOutput();
        }
    }
}
