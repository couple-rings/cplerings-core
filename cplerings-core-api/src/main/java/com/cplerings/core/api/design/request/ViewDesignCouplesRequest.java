package com.cplerings.core.api.design.request;

import java.math.BigDecimal;

import com.cplerings.core.api.shared.AbstractPaginatedRequest;
import com.cplerings.core.common.pagination.FilterableByPrice;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public class ViewDesignCouplesRequest extends AbstractPaginatedRequest implements FilterableByPrice {

    private Long collectionId;
    private Long metalSpecificationId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}
