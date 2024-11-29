package com.cplerings.core.application.design.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewDesignCollectionsInput extends AbstractPaginatedInput {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewDesignCollectionsInput> {

        @Override
        protected ViewDesignCollectionsInput getRequestInstance() {
            return new ViewDesignCollectionsInput();
        }
    }
}
