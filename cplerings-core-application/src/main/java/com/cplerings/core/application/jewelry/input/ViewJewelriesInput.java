package com.cplerings.core.application.jewelry.input;

import com.cplerings.core.application.shared.entity.jewelry.AJewelryStatus;
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
public class ViewJewelriesInput extends AbstractPaginatedInput {

    private Long branchId;
    private Long designId;
    private Long metalSpecId;
    private AJewelryStatus status;

    public static Builder builder() {
        return new Builder();
    }

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewJewelriesInput> {

        private Long branchId;
        private Long designId;
        private Long metalSpecId;
        private AJewelryStatus status;

        public Builder branchId(Long branchId) {
            this.branchId = branchId;
            return self();
        }

        public Builder designId(Long designId) {
            this.designId = designId;
            return self();
        }

        public Builder metalSpecId(Long metalSpecId) {
            this.metalSpecId = metalSpecId;
            return self();
        }

        public Builder status(AJewelryStatus status) {
            this.status = status;
            return self();
        }

        @Override
        public ViewJewelriesInput build() {
            final ViewJewelriesInput input = super.build();
            input.setStatus(status);
            input.setBranchId(branchId);
            input.setDesignId(designId);
            input.setMetalSpecId(metalSpecId);
            return input;
        }

        @Override
        protected ViewJewelriesInput getRequestInstance() {
            return new ViewJewelriesInput();
        }
    }
}
