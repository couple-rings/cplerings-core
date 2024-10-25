package com.cplerings.core.application.design.input;

import com.cplerings.core.application.shared.pagination.AbstractPaginatedInput;
import com.cplerings.core.common.pagination.FilterableByPrice;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewCoupleDesignInput extends AbstractPaginatedInput implements FilterableByPrice {

    private Long collectionId;
    private Long metalSpecificationId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;

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

        @Override
        public ViewCoupleDesignInput build() {
            final ViewCoupleDesignInput input = super.build();
            input.setCollectionId(collectionId);
            input.setMetalSpecificationId(metalSpecificationId);
            input.setMinPrice(minPrice);
            input.setMaxPrice(maxPrice);
            return input;
        }

        @Override
        protected ViewCoupleDesignInput getRequestInstance() {
            return new ViewCoupleDesignInput();
        }
    }
}
