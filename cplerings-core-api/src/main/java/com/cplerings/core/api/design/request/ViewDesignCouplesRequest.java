package com.cplerings.core.api.design.request;

import com.cplerings.core.api.shared.AbstractPaginatedRequest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ViewDesignCouplesRequest extends AbstractPaginatedRequest {

    private Long collectionId;
    private Integer metalSpecificationId;
    private Double minPrice;
    private Double maxPrice;
}
