package com.cplerings.core.application.design.input;

import java.math.BigDecimal;

import com.cplerings.core.application.shared.entity.design.ADesignStatus;
import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;
import com.cplerings.core.common.pagination.FilterableByPrice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewCoupleDesignInput extends AbstractPaginatedInput implements FilterableByPrice {

    private Long collectionId;
    private Long metalSpecificationId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private ADesignStatus status;

    public static Builder builder() {
        return new Builder();
    }

    @Getter(AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static final class Builder extends AbstractPaginatedInputBuilder<Builder, ViewCoupleDesignInput> {

        private Long collectionId;
        private Long metalSpecificationId;
        private BigDecimal minPrice;
        private BigDecimal maxPrice;
        private ADesignStatus status;

        public Builder collectionId(Long collectionId) {
            this.collectionId = collectionId;
            return self();
        }

        public Builder metalSpecificationId(Long metalSpecificationId) {
            this.metalSpecificationId = metalSpecificationId;
            return self();
        }

        public Builder minPrice(BigDecimal minPrice) {
            this.minPrice = minPrice;
            return self();
        }

        public Builder maxPrice(BigDecimal maxPrice) {
            this.maxPrice = maxPrice;
            return self();
        }

        public Builder status(ADesignStatus status) {
            this.status = status;
            return self();
        }

        @Override
        public ViewCoupleDesignInput build() {
            final ViewCoupleDesignInput input = super.build();
            input.setCollectionId(collectionId);
            input.setMetalSpecificationId(metalSpecificationId);
            input.setMinPrice(minPrice);
            input.setMaxPrice(maxPrice);
            input.setStatus(status);
            return input;
        }

        @Override
        protected ViewCoupleDesignInput getRequestInstance() {
            return new ViewCoupleDesignInput();
        }
    }
}
