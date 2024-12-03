package com.cplerings.core.application.jewelry.output;

import com.cplerings.core.application.shared.entity.jewelry.AJewelryCategory;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedOutput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewJewelryCategoriesOutput extends AbstractPaginatedOutput<AJewelryCategory> {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedOutputBuilder<Builder, ViewJewelryCategoriesOutput, AJewelryCategory> {

        @Override
        protected ViewJewelryCategoriesOutput getOutputInstance() {
            return new ViewJewelryCategoriesOutput();
        }
    }
}
