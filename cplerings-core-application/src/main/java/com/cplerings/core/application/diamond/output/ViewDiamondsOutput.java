package com.cplerings.core.application.diamond.output;

import com.cplerings.core.application.shared.entity.design.ADiamond;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewDiamondsOutput extends AbstractPaginatedOutput<ADiamond> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewDiamondsOutput, ADiamond> {

        @Override
        protected ViewDiamondsOutput getOutputInstance() {
            return new ViewDiamondsOutput();
        }
    }
}
