package com.cplerings.core.application.design.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewDesignVersionsInput extends AbstractPaginatedInput {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewDesignVersionsInput> {

        @Override
        protected ViewDesignVersionsInput getRequestInstance() {
            return new ViewDesignVersionsInput();
        }
    }
}
