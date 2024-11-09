package com.cplerings.core.application.diamond.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewDiamondSpecificationInput extends AbstractPaginatedInput {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewDiamondSpecificationInput> {

        @Override
        protected ViewDiamondSpecificationInput getRequestInstance() {
            return new ViewDiamondSpecificationInput();
        }
    }
}