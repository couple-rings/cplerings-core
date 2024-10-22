package com.cplerings.core.api.design.request;

import com.cplerings.core.api.shared.AbstractPaginatedRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ViewDesignCouplesRequest extends AbstractPaginatedRequest<ViewDesignCouplesRequest, ViewDesignCouplesRequest.ViewDesignCouplesRequestBuilder> {

    private Long collectionId;
    private Integer metalSpecificationId;
    private Double minPrice;
    private Double maxPrice;

    // Builder class extending AbstractBuilder
    public static class ViewDesignCouplesRequestBuilder extends AbstractBuilder<ViewDesignCouplesRequest, ViewDesignCouplesRequestBuilder> {

        public ViewDesignCouplesRequestBuilder() {
            super(new ViewDesignCouplesRequest());
        }

        public ViewDesignCouplesRequestBuilder collectionId(Long collectionId) {
            instance.collectionId = collectionId;
            return self();
        }

        public ViewDesignCouplesRequestBuilder metalSpecificationId(Integer metalSpecificationId) {
            instance.metalSpecificationId = metalSpecificationId;
            return self();
        }

        public ViewDesignCouplesRequestBuilder minPrice(Double minPrice) {
            instance.minPrice = minPrice;
            return self();
        }

        public ViewDesignCouplesRequestBuilder maxPrice(Double maxPrice) {
            instance.maxPrice = maxPrice;
            return self();
        }

        @Override
        protected ViewDesignCouplesRequestBuilder self() {
            return this;
        }
    }
}
