package com.cplerings.core.application.jewelry.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewJewelryCategoriesInput extends AbstractPaginatedInput {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewJewelryCategoriesInput> {

        @Override
        protected ViewJewelryCategoriesInput getRequestInstance() {
            return new ViewJewelryCategoriesInput();
        }
    }
}
