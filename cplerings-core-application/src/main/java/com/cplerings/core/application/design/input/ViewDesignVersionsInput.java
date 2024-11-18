package com.cplerings.core.application.design.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewDesignVersionsInput extends AbstractPaginatedInput {

    private Long designId;
    private Long customerId;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewDesignVersionsInput> {

        private Long designId;
        private Long customerId;

        public Builder designId(Long designId) {
            this.designId = designId;
            return self();
        }

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return self();
        }

        @Override
        public ViewDesignVersionsInput build() {
            final ViewDesignVersionsInput input = super.build();
            input.setDesignId(designId);
            input.setCustomerId(customerId);
            return input;
        }

        @Override
        protected ViewDesignVersionsInput getRequestInstance() {
            return new ViewDesignVersionsInput();
        }
    }
}
