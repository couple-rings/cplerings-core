package com.cplerings.core.application.jewelry.output;

import com.cplerings.core.application.shared.entity.jewelry.AJewelry;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewJewelriesOutput extends AbstractPaginatedOutput<AJewelry> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewJewelriesOutput, AJewelry> {

        @Override
        protected ViewJewelriesOutput getOutputInstance() {
            return new ViewJewelriesOutput();
        }
    }
}
