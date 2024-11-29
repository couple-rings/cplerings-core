package com.cplerings.core.application.design.input;

import com.cplerings.core.application.shared.entity.design.ADesignStatus;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewDesignsInput extends AbstractPaginatedInput {

    private ADesignStatus status;
    private Integer size;
    private Long designCollectionId;

    public static Builder builder() {
        return new Builder();
    }

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewDesignsInput> {

        private ADesignStatus status;
        private Integer size;
        private Long designCollectionId;

        public Builder status(ADesignStatus status) {
            this.status = status;
            return self();
        }

        public Builder size(Integer size) {
            this.size = size;
            return self();
        }

        public Builder designCollectionId(Long designCollectionId) {
            this.designCollectionId = designCollectionId;
            return self();
        }

        @Override
        public ViewDesignsInput build() {
            final ViewDesignsInput input = super.build();
            input.setSize(size);
            input.setStatus(status);
            input.setDesignCollectionId(designCollectionId);
            return input;
        }

        @Override
        protected ViewDesignsInput getRequestInstance() {
            return new ViewDesignsInput();
        }
    }
}
