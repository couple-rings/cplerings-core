package com.cplerings.core.application.metal.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewMetalSpecificationInput extends AbstractPaginatedInput {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewMetalSpecificationInput> {

        @Override
        protected ViewMetalSpecificationInput getRequestInstance() {
            return new ViewMetalSpecificationInput();
        }
    }
}
