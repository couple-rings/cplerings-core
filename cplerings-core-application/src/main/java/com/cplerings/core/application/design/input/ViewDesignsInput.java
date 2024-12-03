package com.cplerings.core.application.design.input;

import com.cplerings.core.application.shared.entity.design.ADesignCharacteristic;
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
    private Long categoryId;
    private Long metalSpecId;
    private ADesignCharacteristic characteristic;
    private String name;

    public static Builder builder() {
        return new Builder();
    }

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewDesignsInput> {

        private ADesignStatus status;
        private Integer size;
        private Long designCollectionId;
        private Long categoryId;
        private Long metalSpecId;
        private ADesignCharacteristic characteristic;
        private String name;

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

        public Builder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return self();
        }

        public Builder metalSpecId(Long metalSpecId) {
            this.metalSpecId = metalSpecId;
            return self();
        }

        public Builder characteristic(ADesignCharacteristic characteristic) {
            this.characteristic = characteristic;
            return self();
        }

        public Builder name(String name) {
            this.name = name;
            return self();
        }

        @Override
        public ViewDesignsInput build() {
            final ViewDesignsInput input = super.build();
            input.setSize(size);
            input.setStatus(status);
            input.setDesignCollectionId(designCollectionId);
            input.setCategoryId(categoryId);
            input.setMetalSpecId(metalSpecId);
            input.setCharacteristic(characteristic);
            input.setName(name);
            return input;
        }

        @Override
        protected ViewDesignsInput getRequestInstance() {
            return new ViewDesignsInput();
        }
    }
}
